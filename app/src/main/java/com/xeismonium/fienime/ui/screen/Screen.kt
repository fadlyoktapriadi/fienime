package com.xeismonium.fienime.ui.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
}