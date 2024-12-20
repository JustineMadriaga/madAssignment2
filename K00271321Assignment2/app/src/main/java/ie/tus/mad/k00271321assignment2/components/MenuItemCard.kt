package ie.tus.mad.k00271321assignment2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ie.tus.mad.k00271321assignment2.data.MenuItem

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Price: ${menuItem.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Image(
                    painter = rememberAsyncImagePainter(menuItem.imageUrl),
                    contentDescription = menuItem.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
            // Favorite Icon
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Yellow else Color.Gray
                )
            }
        }
    }
}