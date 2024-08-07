package com.example.fienime

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fienime.ui.screen.Screen
import com.example.fienime.ui.screen.component.BottomBar
import com.example.fienime.ui.screen.detail.DetailScreen
import com.example.fienime.ui.screen.home.HomeScreen
import com.example.fienime.ui.screen.profile.ProfileScreen
import com.example.fienime.ui.theme.FienimeTheme

@Composable
fun MovieApp(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(
                navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
                    .size(65.dp)
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable("detail/{animeId}") { backStackEntry ->
                DetailScreen(navController, backStackEntry.arguments?.getString("animeId") ?: "")
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieAppPreview() {
    FienimeTheme {
        MovieApp(modifier = Modifier)
    }
}