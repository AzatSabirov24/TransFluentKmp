package com.ascoding.transfluent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.LoginScreen
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.RegisterScreen
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.SplashScreen
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.WelcomeScreen
import com.ascoding.transfluent.features.profile.ui.ProfileScreen

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen { }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen { }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen { }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        isLoading = true
    )
}