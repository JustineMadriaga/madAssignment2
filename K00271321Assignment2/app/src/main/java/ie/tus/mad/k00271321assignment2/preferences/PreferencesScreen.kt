package ie.tus.mad.k00271321assignment2.preferences

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ie.tus.mad.k00271321assignment2.menu.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen(
    navController: NavController,
    menuViewModel: MenuViewModel
) {
    val veganOnly by menuViewModel.veganOnly.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preferences") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Food Preferences",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Toggle Vegan-Only Preference
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Vegan Only")
                Switch(
                    checked = veganOnly,
                    onCheckedChange = { isChecked ->
                        menuViewModel.setVeganOnlyPreference(isChecked)
                    }
                )
            }
        }
    }
}