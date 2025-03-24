package com.ascoding.transfluent.features.authentication.presentation.register_login.reducer

import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.authentication.domain.Credentials
import com.ascoding.transfluent.features.authentication.domain.EmailValidator
import com.ascoding.transfluent.features.authentication.domain.PasswordValidator
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthEvent
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthState
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginOrRegisterAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AuthActionReducer(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {

    fun reduce(
        scope: CoroutineScope,
        state: AuthState,
        action: AuthAction,
        authManager: AuthManager?,
        dispatchEvent: (AuthEvent) -> Unit
    ): AuthState {
        return when (action) {

            is LoginOrRegisterAction.UserAuthenticationChecking -> {
                state.copy(isUserAuthenticated = authManager?.userAuthId?.isNotBlank() ?: false)
            }

            is AuthAction.OnEnterEmail -> onEnterEmail(
                state = state,
                email = action.email
            )

            is AuthAction.OnEnterPassword -> onEnterPassword(
                state = state,
                password = action.password
            )

            is LoginOrRegisterAction.OnRegisterClick -> {
                dispatchEvent(AuthEvent.Loading)
                scope.launch {
                    authManager?.register(
                        scope = scope,
                        credentials = Credentials(
                            email = action.email,
                            password = action.password
                        ),
                        onSuccess = {
                            dispatchEvent(AuthEvent.AuthSuccess)
                        },
                        onFailure = { exception ->
                            dispatchEvent(AuthEvent.AuthError(exception.message))
                        }
                    )
                }
                return state
            }

            is LoginOrRegisterAction.OnLoginOrRegisterWithEmailClick -> {
                dispatchEvent(AuthEvent.Loading)
                scope.launch {
                    authManager?.login(
                        scope = scope,
                        credentials = Credentials(
                            email = action.email,
                            password = action.password
                        ),
                        onSuccess = {
                            dispatchEvent(AuthEvent.AuthSuccess)
                        },
                        onFailure = { exception ->
                            dispatchEvent(AuthEvent.AuthError(exception.message))
                        }
                    )
                }
                return state
            }

            LoginOrRegisterAction.OnContinueWithGoogleClick -> {
                scope.launch {
                    authManager?.login(
                        scope = scope,
                        credentials = null,
                        onSuccess = {
                            dispatchEvent(AuthEvent.AuthSuccess)
                        },
                        onFailure = { exception ->
                            dispatchEvent(AuthEvent.AuthError(exception.message))
                        }
                    )
                }
                return state
            }

            is AuthAction.OnAlreadyRegisteredClick -> {
                dispatchEvent(AuthEvent.UserAlreadyHasAnAccount)
                return state
            }

            is AuthAction.BackFromLoginClick -> return state

            AuthAction.BackFromRegisterClick -> {
                dispatchEvent(AuthEvent.BackFromRegister)
                return state
            }

            AuthAction.PasswordVisibilityClick -> {
                dispatchEvent(AuthEvent.HandlingPasswordVisibility)
                return state
            }

            AuthAction.OnContinueWithEmailClick -> {
                dispatchEvent(AuthEvent.ContinuingWithEmail)
                return state
            }
        }
    }

    private fun onEnterEmail(state: AuthState, email: String): AuthState {
        val isEmailValid = emailValidator.validateEmail(email)
        return state.copy(
            email = email,
            isEmailValid = isEmailValid
        )
    }

    private fun onEnterPassword(state: AuthState, password: String): AuthState {
        val hasEightSymbols =
            passwordValidator.containsAtLeastSixCharacters(password)
        val hasAtLeastOneLetter =
            passwordValidator.containsAtLeastOneLetter(password)
        val hasAtLeastOneDigit = passwordValidator.containsAtLeastOneDigit(password)
        val isPasswordValid = passwordValidator.validatePassword(password)
        return state.copy(
            password = password,
            isPasswordValid = isPasswordValid,
            hasPasswordEightSymbols = hasEightSymbols,
            hasPasswordLetter = hasAtLeastOneLetter,
            hasPasswordDigit = hasAtLeastOneDigit
        )
    }
}
