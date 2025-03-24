package com.ascoding.transfluent.features.authentication.presentation.register_login

data class AuthState(
    val isUserAuthenticated: Boolean? = null,
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val hasPasswordEightSymbols: Boolean = false,
    val hasPasswordDigit: Boolean = false,
    val hasPasswordLetter: Boolean = false,
)
