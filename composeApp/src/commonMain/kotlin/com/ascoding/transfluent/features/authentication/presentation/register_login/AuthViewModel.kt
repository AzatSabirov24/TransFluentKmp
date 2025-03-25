package com.ascoding.transfluent.features.authentication.presentation.register_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authManagerFactory: AuthManagerFactory,
    private val actionReducer: AuthActionReducer
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<AuthEvent>()
    val events = _events.asSharedFlow()

    fun onAction(action: AuthAction) {
        viewModelScope.launch {
            _state.update { state ->
                val authManager = when (action) {
                    is LoginOrRegisterAction -> authManagerFactory.authManager(action)
                    else -> null
                }

                actionReducer.reduce(
                    scope = viewModelScope,
                    state = state,
                    action = action,
                    authManager = authManager,
                    dispatchEvent = { event ->
                        viewModelScope.launch {
                            _events.emit(event)
                        }
                    }
                )
            }
        }
    }
}
