@file:OptIn(ExperimentalTestApi::class)

package com.ascoding.transfluent.features.authorization.authentication.presentation.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.ascoding.transfluent.features.authentication.presentation.register_login.AuthState
import com.ascoding.transfluent.features.authentication.presentation.register_login.ui.RegisterScreen
import kotlin.test.Test

class RegisterScreenTest {

    @Test
    fun testValidEmailDisplaysCorrect() = runComposeUiTest {
        val state = AuthState(isEmailValid = true)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Valid email")
            .assertExists()
        onNodeWithContentDescription("Valid email")
            .assertExists()
        onNodeWithContentDescription("register loader")
            .assertDoesNotExist()
    }

    @Test
    fun testInvalidEmailDisplaysCorrect() = runComposeUiTest {
        val state = AuthState(isEmailValid = false)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Invalid email")
            .assertExists()
        onNodeWithContentDescription("Invalid email")
            .assertExists()
    }

    @Test
    fun testMinimumCharactersInPasswordDisplaysCorrect() = runComposeUiTest {
        val state = AuthState(hasPasswordEightSymbols = true)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Minimum 8 characters in password")
            .assertExists()
        onNodeWithContentDescription("Password has 8 characters")
            .assertExists()
    }

    @Test
    fun testMinimumCharactersInPasswordDisplaysIncorrect() = runComposeUiTest {
        val state = AuthState(hasPasswordEightSymbols = false)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Minimum 8 characters in password")
            .assertExists()
        onNodeWithContentDescription("Password does not have 8 characters")
            .assertExists()
    }

    @Test
    fun testMinimumLettersInPasswordDisplaysCorrect() = runComposeUiTest {
        val state = AuthState(hasPasswordLetter = true)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Minimum 1 letter in password")
            .assertExists()
        onNodeWithContentDescription("Password has minimum one letter")
            .assertExists()
    }

    @Test
    fun testMinimumLettersInPasswordDisplaysIncorrect() = runComposeUiTest {
        val state = AuthState(hasPasswordLetter = false)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Minimum 1 letter in password")
            .assertExists()
        onNodeWithContentDescription("Password does not have minimum one letter")
            .assertExists()
    }

    @Test
    fun testMinimumDigitsInPasswordDisplaysCorrect() = runComposeUiTest {
        val state = AuthState(hasPasswordDigit = true)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Minimum 1 digit in password")
            .assertExists()
        onNodeWithContentDescription("Password has minimum one digit")
            .assertExists()
    }

    @Test
    fun testMinimumDigitsInPasswordDisplaysIncorrect() = runComposeUiTest {
        val state = AuthState(hasPasswordDigit = false)

        setContent {
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Minimum 1 digit in password")
            .assertExists()
        onNodeWithContentDescription("Password does not have minimum one digit")
            .assertExists()
    }

    @Test
    fun testButtonSignUpEnabled() = runComposeUiTest {
        setContent {
            val state = AuthState(
                isEmailValid = true,
                isPasswordValid = true
            )
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Sign up")
            .assertIsEnabled()
    }

    @Test
    fun testButtonSignUpDisabledIfPasswordInvalid() = runComposeUiTest {
        setContent {
            val state = AuthState(
                isEmailValid = true,
                isPasswordValid = false
            )
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Sign up")
            .assertIsNotEnabled()
    }

    @Test
    fun testButtonSignUpDisabledIfEmailInvalid() = runComposeUiTest {
        setContent {
            val state = AuthState(
                isEmailValid = false,
                isPasswordValid = true
            )
            RegisterScreen(
                state = state
            )
        }

        onNodeWithText("Sign up")
            .assertIsNotEnabled()
    }

    @Test
    fun testLoading() = runComposeUiTest {
        setContent {
            RegisterScreen(
                isLoading = true
            )
        }

        onNodeWithTag("register loader")
            .assertExists()
        onNodeWithText("Sign up")
            .assertDoesNotExist()
    }
}
