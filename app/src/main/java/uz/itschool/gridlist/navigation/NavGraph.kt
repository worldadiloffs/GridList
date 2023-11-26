package uz.itschool.gridlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.itschool.gridlist.model.Product
import uz.itschool.gridlist.screens.MainScreen
import uz.itschool.gridlist.screens.MoreScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.More.route, arguments = listOf(
            navArgument("product"){
                type = NavType.StringType
            }
        )){entry ->
            val product = entry.arguments?.getSerializable("product")!! as Product
            MoreScreen(navController = navController, product)
        }
    }
}