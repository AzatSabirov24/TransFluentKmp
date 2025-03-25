@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ascoding.transfluent.features.authorization.authentication.presentation

import com.ascoding.transfluent.features.authentication.domain.EmailValidator
import com.ascoding.transfluent.features.authentication.domain.PasswordValidator
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthViewModel
import com.ascoding.transfluent.features.authentication.presentation.register_login.LoginOrRegisterAction
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthActionReducer
import com.ascoding.transfluent.features.authorization.authentication.data.FakeAuthManager
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.equal
import com.ascoding.transfluent.utils.should
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class AuthViewModelTest {

    private lateinit var viewModel: AuthViewModel
    private lateinit var dispatcher: TestDispatcher
    private lateinit var fakeAuthManager: FakeAuthManager
    private lateinit var fakeAuthManagerFactory: FakeAuthManagerFactory
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val authActionReducer = AuthActionReducer(emailValidator, passwordValidator)

    @BeforeTest
    fun setUp() {
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(authId: String?) {
        fakeAuthManager = FakeAuthManager(authId)
        fakeAuthManagerFactory = FakeAuthManagerFactory(fakeAuthManager)
        viewModel = AuthViewModel(
            actionReducer = authActionReducer,
            authManagerFactory = fakeAuthManagerFactory
        )
    }

    @Test
    fun testUserAuthenticationChecking() = runTest {
        // Given
        backgroundScope.launch {
            viewModel.state.collect {}
        }
        createViewModel(authId = "fakeId")

        // When
        viewModel.onAction(action = LoginOrRegisterAction.UserAuthenticationChecking)
        advanceUntilIdle()

        // Then
        val isUserAuthenticated = viewModel.state.value.isUserAuthenticated
        isUserAuthenticated should beTrue(
            message = "authId should not be null, blank or empty"
        )
    }

    @Test
    fun testFailIfIdIsBlank() = runTest {
        // Given
        backgroundScope.launch {
            viewModel.state.collect {}
        }
        createViewModel(authId = " ")

        // When
        viewModel.onAction(action = LoginOrRegisterAction.UserAuthenticationChecking)
        advanceUntilIdle()

        // Then
        val isUserAuthenticated = viewModel.state.value.isUserAuthenticated
        isUserAuthenticated should beFalse(
            message = "isUserAuthenticated should be false because auth id is blank"
        )
    }

    @Test
    fun testFailIfIdIsEmpty() = runTest {
        // Given
        backgroundScope.launch {
            viewModel.state.collect {}
        }
        createViewModel(authId = "")

        // When
        viewModel.onAction(action = LoginOrRegisterAction.UserAuthenticationChecking)
        advanceUntilIdle()

        // Then
        val isUserAuthenticated = viewModel.state.value.isUserAuthenticated
        isUserAuthenticated should beFalse(
            message = "isUserAuthenticated should be false because auth id is empty"
        )
    }

    @Test
    fun testFailIfIdIsNull() = runTest {
        // Given
        backgroundScope.launch {
            viewModel.state.collect {}
        }
        createViewModel(authId = null)

        // When
        viewModel.onAction(action = LoginOrRegisterAction.UserAuthenticationChecking)
        advanceUntilIdle()

        // Then
        val isUserAuthenticated = viewModel.state.value.isUserAuthenticated
        isUserAuthenticated should beFalse(
            message = "isUserAuthenticated should be null because auth id is null"
        )
    }

    @Test
    fun testEmailIsStoredCorrectlyInState() = runTest {
        // Given
        backgroundScope.launch {
            viewModel.state.collect {}
        }
        createViewModel(authId = "qqq1111")
        val email = "email@mail.com"

        // When
        viewModel.onAction(AuthAction.OnEnterEmail(email = email))
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        state.email should equal(email)
        state.isEmailValid should beTrue("The email should be marked as valid")
    }

    @Test
    fun testPasswordIsStoredCorrectlyInState() = runTest {
        // Given
        backgroundScope.launch {
            viewModel.state.collect {}
        }
        createViewModel(authId = "qqq1111")
        val password = "qqqq1111"

        // When
        viewModel.onAction(AuthAction.OnEnterPassword(password = password))
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        state.password should equal(password)
        state.isPasswordValid should beTrue(
            message = "The password should be marked as valid"
        )
    }
}