package com.threedotz.jetpackmegaman.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailMegaman : Screen("home/{megamanId}") {
        fun createRoute(megamanId: Long) = "home/$megamanId"
    }
}