package ie.tus.mad.k00271321assignment2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.tus.mad.k00271321assignment2.home.HomeScreen
import ie.tus.mad.k00271321assignment2.login.LoginScreen
import ie.tus.mad.k00271321assignment2.menu.MenuScreen

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("menu") { MenuScreen(navController, viewModel) }
    }
}