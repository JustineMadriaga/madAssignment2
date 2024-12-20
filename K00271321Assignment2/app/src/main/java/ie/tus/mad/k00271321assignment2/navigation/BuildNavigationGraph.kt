package ie.tus.mad.k00271321assignment2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import ie.tus.mad.k00271321assignment2.favorites.FavoritesScreen
import ie.tus.mad.k00271321assignment2.home.HomeScreen
import ie.tus.mad.k00271321assignment2.home.HomeViewModel
import ie.tus.mad.k00271321assignment2.login.LoginScreen
import ie.tus.mad.k00271321assignment2.login.LoginViewModel
import ie.tus.mad.k00271321assignment2.login.SignUpScreen
import ie.tus.mad.k00271321assignment2.menu.MenuScreen
import ie.tus.mad.k00271321assignment2.menu.MenuViewModel
import ie.tus.mad.k00271321assignment2.preferences.PreferencesScreen
import kotlinx.coroutines.launch

@Composable
fun BuildNavigationGraph(
    homeViewModel: HomeViewModel = viewModel(),
    menuViewModel: MenuViewModel = viewModel()
) {
    val navController = rememberNavController()

    // Drawer state for the burger menu
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    val topNavRoutes = listOf(Screen.Home.route, Screen.Menu.route, Screen.Favorites.route)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = navController, // Pass navController
                onLogout = { // Pass logout logic
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onPreferences = { // Navigate to Preferences
                    scope.launch { drawerState.close() } // Close the drawer
                    navController.navigate(Screen.Preferences.route)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                if (currentDestination in topNavRoutes) {
                    TopNavBar(
                        onMenuClick = { scope.launch { drawerState.open() } }
                    )
                }
            },
            bottomBar = {
                val bottomNavRoutes = listOf(Screen.Home.route, Screen.Menu.route, Screen.Favorites.route)
                if (currentDestination in bottomNavRoutes) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Login.route) { LoginScreen(navController) }
                composable(Screen.SignUp.route) { SignUpScreen(navController) }
                composable(Screen.Home.route) { HomeScreen(navController, menuViewModel) }
                composable(Screen.Menu.route) { MenuScreen(navController, menuViewModel) }
                composable(Screen.Favorites.route) { FavoritesScreen(navController) }
                composable(Screen.Preferences.route) {
                    PreferencesScreen(navController, menuViewModel)
                }
            }
        }
    }
}