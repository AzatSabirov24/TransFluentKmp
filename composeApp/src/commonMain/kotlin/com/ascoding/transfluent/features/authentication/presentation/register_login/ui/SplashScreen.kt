package com.ascoding.transfluent.features.authentication.presentation.register_login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginOrRegisterAction
import com.ascoding.transfluent.theme.Primary
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import transfluent.composeapp.generated.resources.Res
import transfluent.composeapp.generated.resources.ic_logo

@Composable
fun SplashScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    navigateFromSplash: (Boolean) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    state.isUserAuthenticated?.let { navigateFromSplash.invoke(it) }

    SplashScreen(viewModel::onAction)
}

@Composable
fun SplashScreen(
    onAction: (AuthAction) -> Unit = {},
) {
    onAction.invoke(LoginOrRegisterAction.UserAuthenticationChecking)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.ic_logo),
            contentDescription = "Logo"
        )
    }
}
