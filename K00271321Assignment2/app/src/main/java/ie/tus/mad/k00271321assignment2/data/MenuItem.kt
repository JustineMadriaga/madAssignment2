package ie.tus.mad.k00271321assignment2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItem(
    @PrimaryKey val id: String,
    val name: String,
    val description: String = "",
    val price: Double,
    val imageUrl: String = ""
)