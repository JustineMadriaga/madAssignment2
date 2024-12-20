package ie.tus.mad.k00271321assignment2.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavController
import ie.tus.mad.k00271321assignment2.components.MenuItemCard
import ie.tus.mad.k00271321assignment2.data.MenuItem
import ie.tus.mad.k00271321assignment2.navigation.TopNavBar

@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = viewModel()) {
    val menuItems by viewModel.weeklyMenu.collectAsState()
    val favoriteItems by viewModel.favoriteItems.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            menuItems.forEach { (day, items) ->
                item {
                    Text(
                        text = day,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                items(items) { menuItem ->
                    MenuItemCard(
                        menuItem = menuItem,
                        isFavorite = favoriteItems.any { it.id == menuItem.id },
                        onFavoriteClick = { viewModel.toggleFavorite(menuItem) }
                    )
                }
            }
        }
    }