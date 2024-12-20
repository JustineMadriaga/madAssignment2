package ie.tus.mad.k00271321assignment2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(menuItems: List<MenuItem>)

    @Query("SELECT * FROM menu_items")
    suspend fun getAllMenuItems(): List<MenuItem>

    @Query("DELETE FROM menu_items")
    suspend fun clearMenuItems()
}