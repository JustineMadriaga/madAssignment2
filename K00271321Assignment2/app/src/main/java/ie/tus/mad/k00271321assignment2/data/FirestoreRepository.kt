package ie.tus.mad.k00271321assignment2.data

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository(private val context: Context) {
    private val db = FirebaseFirestore.getInstance()
    private val roomDb = AppDatabase.getInstance(context)
    private val menuItemDao = roomDb.menuItemDao()

    suspend fun fetchMenuItems(): List<MenuItem> {
        return try {
            // Fetch from Firestore
            val snapshot = db.collection("menu_items").get().await()
            val menuItems = snapshot.documents.mapNotNull { it.toObject(MenuItem::class.java) }

            // Cache in Room
            menuItemDao.clearMenuItems()
            menuItemDao.insertMenuItems(menuItems)

            // Return the menu items
            menuItems
        } catch (e: Exception) {
            // On failure, return cached items from Room
            menuItemDao.getAllMenuItems()
        }
    }
}