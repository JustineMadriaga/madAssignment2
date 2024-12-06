package ie.tus.mad.k00271321assignment2.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MenuScreen(navController: NavHostController, viewModel: MainViewModel) {
    val dishes = listOf(
        "Spice Bag" to R.drawable.spice_bag, // Replace with image resources
        "Fish & Chips" to R.drawable.fish_and_chips
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(dishes) { dish ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { /* Handle favorite */ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = dish.second),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp)
                )
                Column {
                    Text(dish.first)
                    Text("Serving from 12pm - 4pm")
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.star_icon), // Replace with actual star icon
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}