package uz.itschool.gridlist.navigation

sealed class Screens(val route: String) {
    object Main : Screens("main_screen")
    object More : Screens("more_screen" + "/{product}")
}