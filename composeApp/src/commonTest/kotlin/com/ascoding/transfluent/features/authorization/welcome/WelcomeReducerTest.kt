package com.ascoding.transfluent.features.authorization.welcome

import com.ascoding.transfluent.features.authentication.presentation.welcome.WelcomeAction
import com.ascoding.transfluent.features.authentication.presentation.welcome.WelcomeActionReducer
import com.ascoding.transfluent.features.authentication.presentation.welcome.WelcomeState
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.should
import kotlin.test.Test

class WelcomeReducerTest {

    private val reducer = WelcomeActionReducer()

    @Test
    fun testStateIsCorrectWhenActionIsOnContinueWithEmailClick() {
        // Given
        val initialState = WelcomeState()
        val action = WelcomeAction.OnContinueWithEmailClick

        // When
        val state = reducer.reduce(initialState, action)

        // Then
        state.isContinueWithEmailSelected should beTrue(
            "state.isContinueWithEmailSelected should be true"
        )
        state.isContinueWithGoogleSelected should beFalse(
            "state.isContinueWithGoogleSelected should be false"
        )
    }

    @Test
    fun testStateIsCorrectWhenActionIsOnContinueWithGoogleClick() {
        // Given
        val initialState = WelcomeState()
        val action = WelcomeAction.OnContinueWithGoogleClick

        // When
        val state = reducer.reduce(initialState, action)

        // Then
        state.isContinueWithEmailSelected should beFalse(
            "state.isContinueWithEmailSelected should be false"
        )
        state.isContinueWithGoogleSelected should beTrue(
            "state.isContinueWithGoogleSelected should be true"
        )
    }

    @Test
    fun testStateIsIncorrectWhenActionIsOnContinueWithGoogleClick() {
        // Given
        val initialState = WelcomeState()
        val action = WelcomeAction.OnContinueWithGoogleClick

        // When
        val state = reducer.reduce(initialState, action)

        // Then
        state.isContinueWithEmailSelected should beFalse(
            "state.isContinueWithEmailSelected should be false"
        )
    }

    @Test
    fun testStateIsIncorrectWhenActionIsOnContinueWithEmailClick() {
        // Given
        val initialState = WelcomeState()
        val action = WelcomeAction.OnContinueWithEmailClick

        // When
        val state = reducer.reduce(initialState, action)

        // Then
        state.isContinueWithGoogleSelected should beFalse(
            "state.isContinueWithGoogleSelected should be false"
        )
    }
}
