package ie.tus.mad.k00271321assignment2.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ie.tus.mad.k00271321assignment2.menu.MenuViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ie.tus.mad.k00271321assignment2.components.MenuItemCard
import ie.tus.mad.k00271321assignment2.navigation.TopNavBar

@Composable
fun FavoritesScreen(navController: NavController, viewModel: MenuViewModel = viewModel()) {
    val favoriteItems by viewModel.favoriteItems.collectAsState()


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (favoriteItems.isEmpty()) {
                item {
                    Text(
                        text = "No favorites yet!",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(favoriteItems) { menuItem ->
                    MenuItemCard(
                        menuItem = menuItem,
                        isFavorite = true, // Favorites screen always shows favorited items
                        onFavoriteClick = { viewModel.toggleFavorite(menuItem) }
                    )
                }
            }
        }
    }