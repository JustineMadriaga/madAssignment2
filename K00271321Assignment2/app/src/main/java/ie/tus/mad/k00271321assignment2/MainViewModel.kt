package ie.tus.mad.k00271321assignment2

import android.content.Context
import androidx.lifecycle.ViewModel
import ie.tus.mad.k00271321assignment2.data.FirestoreRepository
import ie.tus.mad.k00271321assignment2.data.MenuItem

class MainViewModel(context: Context) : ViewModel() {
    private val repository = FirestoreRepository(context)

    suspend fun getMenuItems(): List<MenuItem> {
        return repository.fetchMenuItems()
    }
}