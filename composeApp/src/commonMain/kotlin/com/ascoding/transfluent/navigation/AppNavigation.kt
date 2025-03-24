package com.ascoding.transfluent.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ascoding.transfluent.navigation.authentication.LoginNavigation
import com.ascoding.transfluent.navigation.authentication.RegisterNavigation
import com.ascoding.transfluent.navigation.authentication.SplashNavigation
import com.ascoding.transfluent.navigation.authentication.WelcomeNavigation
import com.ascoding.transfluent.navigation.bottom_bar_navigation.BottomBarNavigation
import com.ascoding.transfluent.navigation.route.Route

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()
    val bottomBarNavController = rememberNavController()
    NavHost(
        navController = appNavController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            SplashNavigation(
                navController = appNavController
            )
        }

        navigation<Route.AuthGraph>(startDestination = Route.Welcome) {
            composable<Route.Welcome> {
                WelcomeNavigation(
                    navController = appNavController
                )
            }
            composable<Route.Register> {
                RegisterNavigation(
                    navController = appNavController
                )
            }
            composable<Route.Login> {
                LoginNavigation(
                    navController = appNavController
                )
            }
        }

        navigation<Route.MainGraph>(startDestination = Route.Translator) {
            composable<Route.Translator> {
                BottomBarNavigation(
                    appNavController = appNavController,
                    bottomBarNavController = bottomBarNavController
                )
            }
        }
    }
}