package com.ascoding.transfluent.navigation.bottom_bar_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ascoding.transfluent.features.profile.ProfileState
import com.ascoding.transfluent.features.profile.ProfileViewModel
import com.ascoding.transfluent.features.profile.ui.ProfileScreenRoot
import com.ascoding.transfluent.navigation.route.Route
import org.koin.compose.koinInject

@Composable
fun ProfileNavigation(navController: NavController) {
    val viewModel = koinInject<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreenRoot(viewModel)

    navigate(state, navController)
}

private fun navigate(state: ProfileState, navController: NavController) {
    when {
        state.isSignedOut -> {
            navController.navigate(Route.AuthGraph) {
                popUpTo(Route.MainGraph) { inclusive = true }
            }
        }
    }
}