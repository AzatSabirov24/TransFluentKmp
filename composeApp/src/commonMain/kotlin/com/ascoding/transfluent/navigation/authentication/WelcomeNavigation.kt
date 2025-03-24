package com.ascoding.transfluent.navigation.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.WelcomeScreenRoot
import com.ascoding.transfluent.navigation.route.Route
import org.koin.compose.koinInject

@Composable
fun WelcomeNavigation(navController: NavController) {
    val viewModel = koinInject<AuthViewModel>()

    WelcomeScreenRoot(viewModel) { route ->
        navigateFromWelcome(
            navController = navController,
            navigationRoute = route
        )
    }
}

private fun navigateFromWelcome(
    navController: NavController,
    navigationRoute: Route
) {
    when (navigationRoute) {
        Route.Register -> navController.navigate(Route.Register)
        else -> Unit
    }
}