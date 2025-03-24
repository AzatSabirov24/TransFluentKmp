package com.ascoding.transfluent.navigation.bottom_bar_navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ascoding.transfluent.features.translator.ui.TranslatorScreen
import com.ascoding.transfluent.navigation.route.Route
import com.ascoding.transfluent.common.utils.extensions.getClassName

@Composable
fun BottomBarNavigation(
    appNavController: NavHostController,
    bottomBarNavController: NavHostController
) {
    val items = listOf(
        BottomNavBarDestination.Translator,
        BottomNavBarDestination.Profile
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.route) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            bottomBarNavController.navigate(screen.route) {
                                popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(
            modifier = modifier,
            navController = bottomBarNavController,
            startDestination = BottomNavBarDestination.Translator.route
        ) {
            composable(route = BottomNavBarDestination.Translator.route) {
                TranslatorScreen()
            }
            composable(route = BottomNavBarDestination.Profile.route) {
                ProfileNavigation(appNavController)
            }
        }
    }
}

sealed class BottomNavBarDestination(
    val route: String,
    val icon: ImageVector
) {
    data object Translator : BottomNavBarDestination(route = getClassName<Route.Translator>(), icon = Icons.Filled.Home)
    data object Profile : BottomNavBarDestination(route = getClassName<Route.Profile>(), icon = Icons.Filled.Person)
}