package com.ascoding.transfluent.features.authentication.presentation.register_login

import com.ascoding.transfluent.features.authentication.domain.AuthManager

class AuthManagerFactoryImpl(
    private val firebaseAuthManager: AuthManager,
    private val googleAuthManager: AuthManager
) : AuthManagerFactory {

    override fun authManager(action: LoginOrRegisterAction): AuthManager {
        return when (action) {
            is LoginOrRegisterAction.OnLoginOrRegisterWithEmailClick -> firebaseAuthManager
            is LoginOrRegisterAction.OnRegisterClick -> firebaseAuthManager
            LoginOrRegisterAction.UserAuthenticationChecking -> firebaseAuthManager
            LoginOrRegisterAction.OnContinueWithGoogleClick -> googleAuthManager
        }
    }
}

interface AuthManagerFactory {

    fun authManager(action: LoginOrRegisterAction): AuthManager
}
