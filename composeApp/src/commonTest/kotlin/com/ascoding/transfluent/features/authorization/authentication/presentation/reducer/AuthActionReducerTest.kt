@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ascoding.transfluent.features.authorization.authentication.presentation.reducer

import com.ascoding.transfluent.features.authentication.domain.EmailValidator
import com.ascoding.transfluent.features.authentication.domain.PasswordValidator
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthEvent
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthState
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginOrRegisterAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.reducer.AuthActionReducer
import com.ascoding.transfluent.features.authorization.authentication.data.FakeAuthManager
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.equal
import com.ascoding.transfluent.utils.notEqual
import com.ascoding.transfluent.utils.should
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class AuthActionReducerTest {

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val authActionReducer = AuthActionReducer(emailValidator, passwordValidator)
    private val testScope = TestScope()
    private lateinit var fakeAuthManager: FakeAuthManager
    private lateinit var dispatcher: TestDispatcher

    @BeforeTest
    fun setUp() {
        fakeAuthManager = FakeAuthManager("fakeId")
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun reduceState(
        action: AuthAction,
        state: AuthState = AuthState(),
        dispatchEvent: (AuthEvent) -> Unit = {}
    ) = authActionReducer.reduce(
        scope = testScope,
        state = state,
        action = action,
        authManager = fakeAuthManager,
        dispatchEvent = dispatchEvent
    )

    @Test
    fun testEmailIsStoredCorrectlyInState() {
        // Given
        val email = "email@mail.com"
        val state = reduceState(AuthAction.OnEnterEmail(email))

        // Then
        state.email should equal(email)
        state.isEmailValid should beTrue(
            "The email should be marked as valid"
        )
    }

    @Test
    fun testEmailIsNotCorrectlyStoredInState() {
        // Given
        val email = "email@mail.com"
        val state = reduceState(AuthAction.OnEnterEmail("email"))

        // Then
        state.email should notEqual(email)
        state.isEmailValid should beFalse(
            "The email should not be marked as valid"
        )
    }

    @Test
    fun testPasswordIsMarkedValidWhenAllConditionsAreMet() {
        // Given
        val validPassword = "Passw123"
        val actualState = reduceState(AuthAction.OnEnterPassword(validPassword))

        // Then
        actualState.password should equal(validPassword)
        actualState.hasPasswordEightSymbols should beTrue(
            "Password should have at least six characters"
        )
        actualState.hasPasswordLetter should beTrue(
            "Password should have at least one letter"
        )
        actualState.hasPasswordDigit should beTrue(
            "Password should have at least one digit"
        )
    }

    @Test
    fun testPasswordIsNotCorrectlyStoredInState() {
        // Given
        val password = "password123"
        val state = reduceState(AuthAction.OnEnterPassword("password"))

        // Then
        state.password should notEqual(password)
        state.isPasswordValid should beFalse(
            "The password should not be marked as valid"
        )
    }

    @Test
    fun testLoadingAndSuccessEventsAreTriggered() = runTest {
        var loadingEventDispatched = false
        var successEventDispatched = false

        authActionReducer.reduce(
            scope = this,
            state = AuthState(),
            action = LoginOrRegisterAction.OnRegisterClick(
                email = "email@mail.com",
                password = "Pass1234"
            ),
            authManager = fakeAuthManager,
            dispatchEvent = { event ->
                when (event) {
                    is AuthEvent.Loading -> loadingEventDispatched = true
                    is AuthEvent.AuthSuccess -> successEventDispatched = true
                    else -> Unit
                }
            }
        )
        fakeAuthManager.simulateSuccess(scope = this)
        advanceUntilIdle()
        loadingEventDispatched should beTrue(
            "Loading event should be dispatched"
        )
        successEventDispatched should beTrue(
            "Success event should be dispatched"
        )
    }

    @Test
    fun testLoadingAndErrorEventsAreTriggered() = runTest {
        var loadingEventDispatched = false
        var errorEventDispatched = false
        var errorMessage = ""

        authActionReducer.reduce(
            scope = this,
            state = AuthState(),
            action = LoginOrRegisterAction.OnRegisterClick(
                email = "email@mail.com",
                password = "Pass1234"
            ),
            authManager = fakeAuthManager,
            dispatchEvent = { event ->
                when (event) {
                    is AuthEvent.Loading -> loadingEventDispatched = true
                    is AuthEvent.AuthError -> {
                        errorEventDispatched = true
                        errorMessage = event.error ?: ""
                    }

                    else -> Unit
                }
            }
        )
        fakeAuthManager.simulateFailure(
            scope = this,
            exception = Exception("test error")
        )
        advanceUntilIdle()
        loadingEventDispatched should beTrue(
            "Loading event should be dispatched"
        )
        errorEventDispatched should beTrue(
            "Success event should be dispatched"
        )
        errorMessage should equal("test error")
    }

    @Test
    fun testPasswordVisibilityEventIsTriggered() = runTest {
        var passwordVisibilityEventDispatched = false

        authActionReducer.reduce(
            scope = this,
            state = AuthState(),
            action = AuthAction.PasswordVisibilityClick,
            authManager = fakeAuthManager,
            dispatchEvent = { event ->
                if (event == AuthEvent.HandlingPasswordVisibility) passwordVisibilityEventDispatched = true
            }
        )

        passwordVisibilityEventDispatched should beTrue(
            "Password visibility event should be dispatched"
        )
    }
}
