package com.ascoding.transfluent.features.authorization.authentication.presentation

import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthManagerFactory
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginOrRegisterAction

class FakeAuthManagerFactory(
    private val authManager: AuthManager
) : AuthManagerFactory {

    override fun authManager(action: LoginOrRegisterAction): AuthManager {
        return authManager
    }
}