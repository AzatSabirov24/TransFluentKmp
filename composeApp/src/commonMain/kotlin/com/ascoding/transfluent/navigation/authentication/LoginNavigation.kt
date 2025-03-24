package com.ascoding.transfluent.navigation.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.LoginScreenRoot
import com.ascoding.transfluent.navigation.route.Route
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginNavigation(navController: NavController) {
    val viewModel = koinViewModel<AuthViewModel>()

    LoginScreenRoot(viewModel) { route ->
        navigateFromLogin(
            navController = navController,
            navigationRoute = route
        )
    }
}

fun navigateFromLogin(
    navController: NavController,
    navigationRoute: Route
) {
    when (navigationRoute) {
        Route.MainGraph -> navController.navigate(Route.MainGraph) {
            popUpTo(Route.AuthGraph) { inclusive = true }
        }
        Route.BackStack -> navController.popBackStack()

        else -> Unit
    }
}
