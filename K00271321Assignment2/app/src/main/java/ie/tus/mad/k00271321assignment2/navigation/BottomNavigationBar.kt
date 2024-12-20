package ie.tus.mad.k00271321assignment2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFFC5A94F),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = navController.currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true,
            onClick = { navController.navigate(Screen.Home.route) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFFC5A94F)
            )
        )
        NavigationBarItem(
            label = { Text("Menu") },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
            selected = navController.currentDestination?.hierarchy?.any { it.route == Screen.Menu.route } == true,
            onClick = { navController.navigate(Screen.Menu.route) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFFC5A94F)
            )
        )
        NavigationBarItem(
            label = { Text("Favorites") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            selected = navController.currentDestination?.hierarchy?.any { it.route == Screen.Favorites.route } == true,
            onClick = { navController.navigate(Screen.Favorites.route) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color(0xFFC5A94F)
            )
        )
    }
}