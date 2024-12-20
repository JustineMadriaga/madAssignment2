package ie.tus.mad.k00271321assignment2.data

class MenuRepository(private val dao: MenuItemDao) {
    suspend fun getMenuItems(): List<MenuItem> = dao.getAllMenuItems()
    suspend fun insertMenuItems(items: List<MenuItem>) = dao.insertMenuItems(items)
}