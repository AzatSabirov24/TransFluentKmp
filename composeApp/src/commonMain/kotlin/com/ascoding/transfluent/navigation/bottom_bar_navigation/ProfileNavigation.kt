package com.ascoding.transfluent.navigation.bottom_bar_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ascoding.transfluent.features.profile.ProfileViewModel
import com.ascoding.transfluent.features.profile.ui.ProfileScreenRoot
import com.ascoding.transfluent.navigation.route.Route
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileNavigation(navController: NavController) {
    val viewModel = koinViewModel<ProfileViewModel>()

    ProfileScreenRoot(viewModel) {
        navigateFromProfile(navController, it)
    }
}

private fun navigateFromProfile(
    navController: NavController,
    navigationRoute: Route
) {
    when (navigationRoute) {
        is Route.AuthGraph -> {
            navController.navigate(Route.AuthGraph) {
                popUpTo(Route.MainGraph) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

        else -> Unit
    }
}
