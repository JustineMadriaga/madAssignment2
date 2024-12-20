package ie.tus.mad.k00271321assignment2.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DrawerContent(
    navController: NavController,
    onLogout: () -> Unit,
    onPreferences: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.7f) // Drawer takes 70% of the screen width
            .background(Color.Black) // Set the background color to black
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Drawer Header
        Text(
            text = "Menu",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White, // White text color
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Divider(color = Color.Gray) // Add a divider with a light gray color

        // Logout Button
        TextButton(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.textButtonColors(contentColor = Color.White) // White text color
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Logout",
                tint = Color.White, // White icon
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Logout")
        }

        // Preferences Button
        TextButton(
            onClick = { onPreferences() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Preferences",
                tint = Color.White, // White icon
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Preferences")
        }
    }
}