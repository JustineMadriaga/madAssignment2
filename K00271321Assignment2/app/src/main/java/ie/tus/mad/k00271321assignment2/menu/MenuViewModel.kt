package ie.tus.mad.k00271321assignment2.menu

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ie.tus.mad.k00271321assignment2.data.AppDatabase
import ie.tus.mad.k00271321assignment2.data.MenuItem
import ie.tus.mad.k00271321assignment2.data.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _weeklyMenu = MutableStateFlow<Map<String, List<MenuItem>>>(emptyMap())
    val weeklyMenu: StateFlow<Map<String, List<MenuItem>>> = _weeklyMenu

    private val _favoriteItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val favoriteItems: StateFlow<List<MenuItem>> = _favoriteItems

    private val _veganOnly = MutableStateFlow(false) // State for vegan-only preference
    val veganOnly: StateFlow<Boolean> = _veganOnly

    private val _dessertOfTheDay = MutableStateFlow<MenuItem?>(null)
    val dessertOfTheDay: StateFlow<MenuItem?> = _dessertOfTheDay

    private val _allMenuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    private val sharedPreferences = application.getSharedPreferences("WeeklyMenu", Context.MODE_PRIVATE)
    private val gson = Gson()

    init {
        // Monitor user authentication changes
        auth.addAuthStateListener {
            loadFavoritesForCurrentUser()
        }
        fetchMenuItems()
    }

    // Fetch all menu items from Firestore
    private fun fetchMenuItems() {
        firestore.collection("menu_items")
            .get()
            .addOnSuccessListener { result ->
                val items = result.toObjects(MenuItem::class.java)
                _allMenuItems.value = items
                loadOrGenerateWeeklyMenu(items)
                loadOrGenerateDessertOfTheDay(items)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    // Load favorites for the currently authenticated user
    private fun loadFavoritesForCurrentUser() {
        val currentUserId = auth.currentUser?.uid

        // Clear previous favorites before loading new data
        _favoriteItems.value = emptyList()

        currentUserId?.let { userId ->
            firestore.collection("favorites")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    val favorites = document.toObject(FavoritesWrapper::class.java)?.items ?: emptyList()
                    _favoriteItems.value = favorites
                }
                .addOnFailureListener { e ->
                    e.printStackTrace()
                }
        }
    }

    fun toggleFavorite(item: MenuItem) {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            val currentFavorites = _favoriteItems.value.toMutableList()

            // Add or remove the item
            if (currentFavorites.any { it.id == item.id }) {
                currentFavorites.removeIf { it.id == item.id } // Remove the item
            } else {
                currentFavorites.add(item) // Add the item
            }

            // Update the state and Firestore
            _favoriteItems.value = currentFavorites.toList() // Trigger recomposition
            saveFavoritesToFirestore(currentUserId, currentFavorites)
        }
    }

    private fun saveFavoritesToFirestore(userId: String, favorites: List<MenuItem>) {
        firestore.collection("favorites")
            .document(userId)
            .set(mapOf("items" to favorites)) // Overwrite with the new list
            .addOnSuccessListener {
                Log.d("Favorites", "Favorites successfully updated in Firestore")
            }
            .addOnFailureListener { e ->
                Log.e("Favorites", "Failed to update favorites in Firestore", e)
            }
    }
    // Wrapper for Firestore favorites data
    data class FavoritesWrapper(val items: List<MenuItem> = emptyList())

    // Load or generate the weekly menu logic
    private fun loadOrGenerateWeeklyMenu(menuItems: List<MenuItem>) {
        val currentWeek = getCurrentWeek()
        val savedWeek = sharedPreferences.getString("week_key", null)

        if (savedWeek == currentWeek) {
            val json = sharedPreferences.getString("menu_for_week", null)
            if (json != null) {
                val menuType = object : TypeToken<Map<String, List<MenuItem>>>() {}.type
                val menu = gson.fromJson<Map<String, List<MenuItem>>>(json, menuType)
                _weeklyMenu.value = menu
            }
        } else {
            saveWeeklyMenu(menuItems, currentWeek)
        }
    }

    private fun saveWeeklyMenu(menuItems: List<MenuItem>, currentWeek: String) {
        val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
        val randomizedMenu = mutableMapOf<String, List<MenuItem>>()

        for (day in daysOfWeek) {
            val nonVeganItems = menuItems.filter { it.type == "non-vegan" }.shuffled()
            val veganItems = menuItems.filter { it.type == "vegan" }.shuffled()

            if (nonVeganItems.isNotEmpty() && veganItems.isNotEmpty()) {
                randomizedMenu[day] = listOf(nonVeganItems.first(), veganItems.first())
            }
        }

        sharedPreferences.edit()
            .putString("menu_for_week", gson.toJson(randomizedMenu))
            .putString("week_key", currentWeek)
            .apply()

        _weeklyMenu.value = randomizedMenu
    }

    private fun getCurrentWeek(): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("yyyy-ww", Locale.getDefault())
        return format.format(calendar.time)
    }

    fun setVeganOnlyPreference(isVeganOnly: Boolean) {
        _veganOnly.value = isVeganOnly
        loadFilteredMenu() // Reload the menu based on the preference
    }

    private fun loadFilteredMenu() {
        val allItems = _allMenuItems.value

        if (_veganOnly.value) {
            // Filter only vegan items for the menu
            val veganMenu = mapOf("Vegan Menu" to allItems.filter { it.type == "vegan" })
            _weeklyMenu.value = veganMenu
        } else {
            // Restore the previously saved weekly menu
            val currentWeek = getCurrentWeek()
            val savedWeek = sharedPreferences.getString("week_key", null)
            val savedMenuJson = sharedPreferences.getString("menu_for_week", null)

            if (savedWeek == currentWeek && savedMenuJson != null) {
                // Load the saved weekly menu
                val menuType = object : TypeToken<Map<String, List<MenuItem>>>() {}.type
                val savedMenu = gson.fromJson<Map<String, List<MenuItem>>>(savedMenuJson, menuType)
                _weeklyMenu.value = savedMenu
            } else {
                // If no saved menu, regenerate the weekly menu
                saveWeeklyMenu(allItems, currentWeek)
            }
        }
    }

    private fun selectDessertOfTheDay(menuItems: List<MenuItem>) {
        val dessertItems = menuItems.filter { it.type == "dessert" }
        if (dessertItems.isNotEmpty()) {
            _dessertOfTheDay.value = dessertItems.random()
        }
    }

    private fun loadOrGenerateDessertOfTheDay(menuItems: List<MenuItem>) {
        val savedDessertJson = sharedPreferences.getString("dessert_of_the_day", null)
        val savedDate = sharedPreferences.getString("dessert_date", null)
        val currentDate = getCurrentDate()

        if (savedDate == currentDate && savedDessertJson != null) {
            // Load saved dessert if the date matches
            val savedDessert = Gson().fromJson(savedDessertJson, MenuItem::class.java)
            _dessertOfTheDay.value = savedDessert
        } else {
            // Select a new dessert and save it
            val dessertItems = menuItems.filter { it.type == "dessert" }
            if (dessertItems.isNotEmpty()) {
                val selectedDessert = dessertItems.random()
                _dessertOfTheDay.value = selectedDessert
                saveDessertOfTheDay(selectedDessert, currentDate)
            }
        }
    }

    private fun saveDessertOfTheDay(dessert: MenuItem, date: String) {
        val dessertJson = Gson().toJson(dessert)
        sharedPreferences.edit()
            .putString("dessert_of_the_day", dessertJson)
            .putString("dessert_date", date)
            .apply()
    }

    // Get the current date as a string
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

}