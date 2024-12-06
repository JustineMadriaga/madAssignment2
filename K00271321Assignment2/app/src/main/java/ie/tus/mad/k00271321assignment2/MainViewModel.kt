package ie.tus.mad.k00271321assignment2

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Example variables for current user session
    var isLoggedIn = false
    var favoriteDishes = mutableListOf<String>() // For favorite dishes in Menu Page
}