package ie.tus.mad.k00271321assignment2.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ie.tus.mad.k00271321assignment2.data.MenuItem
import ie.tus.mad.k00271321assignment2.MainViewModel

@Composable
fun MenuScreen(navController: NavHostController, viewModel: MainViewModel) {
    val menuItems = remember { mutableStateOf<List<MenuItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        menuItems.value = viewModel.getMenuItems()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(menuItems.value) { menuItem ->
            Row(modifier = Modifier.padding(8.dp)) {
                Text(menuItem.name)
                Spacer(modifier = Modifier.weight(1f))
                Text("$${menuItem.price}")
            }
        }
    }
}