package com.ascoding.transfluent.features.authentication.presentation.register_login

import com.ascoding.transfluent.features.authentication.domain.AuthManager

class AuthManagerFactory(
    private val firebaseAuthManager: AuthManager,
    private val googleAuthManager: AuthManager
) {

    fun authManager(action: LoginAction): AuthManager {
        return when (action) {
            is LoginAction.OnLoginWithEmailClick -> firebaseAuthManager
            is LoginAction.OnRegisterClick -> firebaseAuthManager
            LoginAction.UserAuthenticationChecking -> firebaseAuthManager
            LoginAction.OnContinueWithGoogleClick -> googleAuthManager
        }
    }
}
