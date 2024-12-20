package ie.tus.mad.k00271321assignment2.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import ie.tus.mad.k00271321assignment2.menu.MenuViewModel
import ie.tus.mad.k00271321assignment2.navigation.Screen
import ie.tus.mad.k00271321assignment2.navigation.TopNavBar
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable

fun HomeScreen(navController: NavController, viewModel: MenuViewModel = viewModel()) {
    val dessertOfTheDay by viewModel.dessertOfTheDay.collectAsState()

    val tusCanteenLocation = LatLng(52.67526299029107, -8.647038568851933)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Welcome Text
            Text(
                text = "Welcome to TUS Eats!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Dessert of the Day Section
            dessertOfTheDay?.let { dessert ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Dessert of the Day: ${dessert.name}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Price: ${dessert.price}")
                        Image(
                            painter = rememberAsyncImagePainter(model = dessert.imageUrl),
                            contentDescription = "Dessert Image",
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google Maps Section
            Text(
                text = "Find Us Here",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
            ) {
                GoogleMapView(tusCanteenLocation)
            }
        }
    }

@Composable
fun GoogleMapView(location: LatLng) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f) // Zoom level
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = location),
            title = "TUS Canteen",
            snippet = "Come enjoy your meal here!"
        )
    }
}