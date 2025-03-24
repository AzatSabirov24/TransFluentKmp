package com.ascoding.transfluent.features.authentication.presentation.register_login

sealed interface AuthAction {

    data class OnEnterEmail(val email: String) : AuthAction

    data class OnEnterPassword(val password: String) : AuthAction

    data object OnAlreadyRegisteredClick : AuthAction

    data object BackFromRegisterClick : AuthAction

    data object BackFromLoginClick : AuthAction

    data object PasswordVisibilityClick : AuthAction

    data object OnContinueWithEmailClick : AuthAction
}

sealed interface LoginAction : AuthAction {

    data class OnRegisterClick(
        val email: String,
        val password: String,
    ) : LoginAction

    data class OnLoginWithEmailClick(
        val email: String = "",
        val password: String = ""
    ) : LoginAction

    data object OnContinueWithGoogleClick : LoginAction

    data object UserAuthenticationChecking : LoginAction


}