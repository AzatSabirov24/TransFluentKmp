@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ascoding.transfluent.features.profile.presentation

import com.ascoding.transfluent.features.authorization.authentication.data.FakeAuthManager
import com.ascoding.transfluent.features.profile.ProfileEvent
import com.ascoding.transfluent.features.profile.ProfileViewModel
import com.ascoding.transfluent.features.profile.reducer.ProfileActionReducer
import com.ascoding.transfluent.features.profile.reducer.ProfileEventReducer
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.should
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProfileViewModelTest {

    private lateinit var dispatcher: TestDispatcher

    @BeforeTest
    fun setUp() {
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val viewModel = ProfileViewModel(
        actionReducer = ProfileActionReducer(),
        eventReducer = ProfileEventReducer(),
        authManager = FakeAuthManager(userAuthId = "fake id")
    )

    @Test
    fun `should return the correct state when sign out is successful`() = runTest {
        viewModel.onEvent(ProfileEvent.SignOutSuccess)
        advanceUntilIdle()

        val updatedState = viewModel.state.value

        updatedState.isSignedOut should beTrue()
    }

    @Test
    fun `should return the correct state when sign out is failure`() = runTest {
        viewModel.onEvent(ProfileEvent.SignOutError(error = "test error"))
        advanceUntilIdle()

        val updatedState = viewModel.state.value

        updatedState.isSignedOut should beFalse()
    }
}