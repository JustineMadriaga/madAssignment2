package ie.tus.mad.k00271321assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ie.tus.mad.k00271321assignment2.ui.theme.TUSEatsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TUSEatsTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = viewModel()

                NavigationGraph(navController, viewModel)
            }
        }
    }
}