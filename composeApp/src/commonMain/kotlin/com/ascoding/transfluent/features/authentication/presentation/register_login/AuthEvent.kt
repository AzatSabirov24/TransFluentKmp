package com.ascoding.transfluent.features.authentication.presentation.register_login

sealed interface AuthEvent {

    data object Loading : AuthEvent

    data object AuthSuccess : AuthEvent

    data class AuthError(val error: String?) : AuthEvent

    data object UserAlreadyHasAnAccount : AuthEvent

    data object BackFromRegister : AuthEvent

    data object HandlingPasswordVisibility : AuthEvent

    data object ContinuingWithEmail : AuthEvent
}
