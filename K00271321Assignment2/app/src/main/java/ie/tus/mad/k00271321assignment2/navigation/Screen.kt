package ie.tus.mad.k00271321assignment2.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
    data object Menu : Screen("menu")
    data object Favorites : Screen("favorites")
    data object Preferences : Screen("preferences")

}

val screens = listOf(
    Screen.Login,
    Screen.SignUp,
    Screen.Home,
    Screen.Menu,
    Screen.Favorites,
    Screen.Preferences
)