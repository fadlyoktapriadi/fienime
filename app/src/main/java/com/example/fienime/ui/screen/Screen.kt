package com.example.fienime.ui.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{animeId}") {
        fun createRoute(animeId: Long) = "detail/$animeId"
    }
}