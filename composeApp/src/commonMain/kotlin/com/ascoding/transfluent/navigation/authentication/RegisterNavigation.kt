package com.ascoding.transfluent.navigation.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.RegisterScreenRoot
import com.ascoding.transfluent.navigation.route.Route
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterNavigation(navController: NavController) {
    val viewModel = koinViewModel<AuthViewModel>()

    RegisterScreenRoot(viewModel) { route ->
        navigateFromRegister(
            navController = navController,
            navigationRoute = route
        )
    }
}

fun navigateFromRegister(
    navController: NavController,
    navigationRoute: Route
) {
    when (navigationRoute) {
        Route.MainGraph -> navController.navigate(Route.MainGraph) {
            popUpTo(Route.AuthGraph) { inclusive = true }
        }
        Route.Login -> navController.navigate(Route.Login)
        Route.BackStack -> navController.popBackStack()
        else -> Unit
    }
}
