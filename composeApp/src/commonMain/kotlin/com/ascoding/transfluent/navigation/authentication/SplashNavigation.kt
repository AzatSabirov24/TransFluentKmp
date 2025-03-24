package com.ascoding.transfluent.navigation.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.SplashScreenRoot
import com.ascoding.transfluent.navigation.route.Route
import org.koin.compose.koinInject

@Composable
fun SplashNavigation(navController: NavController) {
    val viewModel = koinInject<AuthViewModel>()

    SplashScreenRoot(
        viewModel = viewModel,
        navigateFromSplash = { isAuthenticated ->
            navigateFromSplash(navController, isAuthenticated)
        }
    )
}

fun navigateFromSplash(
    navController: NavController,
    isAuthenticated: Boolean
) {
    val destination = if (isAuthenticated) Route.MainGraph else Route.Welcome
    navController.navigate(destination) {
        popUpTo(Route.Splash) { inclusive = true }
    }
}
