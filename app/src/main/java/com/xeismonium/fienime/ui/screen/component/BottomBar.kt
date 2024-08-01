package com.xeismonium.fienime.ui.screen.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fienime.R
import com.xeismonium.fienime.ui.screen.Screen
import com.xeismonium.fienime.ui.theme.BackgroundPrimary
import com.xeismonium.fienime.ui.theme.Primary

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.clip(RoundedCornerShape(30.dp)),
        containerColor = BackgroundPrimary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                icon = R.drawable.home,
                selectedIcon = R.drawable.home_click,
                screen = Screen.Home
            ),
            NavigationItem(
                icon = R.drawable.profile,
                selectedIcon = R.drawable.profile_click,
                screen = Screen.Profile,
            ),

            )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == item.screen.route)
                            item.selectedIcon else item.icon),
                        contentDescription = "Item",
                        modifier = Modifier.size(27.dp),
                        tint = Primary
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}