package ie.tus.mad.k00271321assignment2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItem(
    @PrimaryKey val id: Int = 0,
    val type: String = "",
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val dayOfWeek: String = ""
)