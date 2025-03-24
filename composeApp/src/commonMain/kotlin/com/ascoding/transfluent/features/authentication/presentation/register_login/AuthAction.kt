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

sealed interface LoginOrRegisterAction : AuthAction {

    data class OnRegisterClick(
        val email: String,
        val password: String,
    ) : LoginOrRegisterAction

    data class OnLoginOrRegisterWithEmailClick(
        val email: String = "",
        val password: String = ""
    ) : LoginOrRegisterAction

    data object OnContinueWithGoogleClick : LoginOrRegisterAction

    data object UserAuthenticationChecking : LoginOrRegisterAction


}