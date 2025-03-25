@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ascoding.transfluent.features.profile.presentation.reducer

import com.ascoding.transfluent.features.authorization.authentication.data.FakeAuthManager
import com.ascoding.transfluent.features.profile.ProfileAction
import com.ascoding.transfluent.features.profile.ProfileEvent
import com.ascoding.transfluent.features.profile.reducer.ProfileActionReducer
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.equal
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

class ProfileActionReducerTest {

    private val reducer = ProfileActionReducer()
    private lateinit var dispatcher: TestDispatcher
    private lateinit var fakeAuthManager: FakeAuthManager

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

    @Test
    fun `should send SignOutSuccess event`() = runTest {
        var isSignOutSuccessDispatched = false

        reducer.reduce(
            scope = this,
            action = ProfileAction.SignOut,
            authManager = fakeAuthManager,
            dispatchEvent = { event ->
                if (event is ProfileEvent.SignOutSuccess) isSignOutSuccessDispatched = true
            }
        )
        fakeAuthManager.simulateSuccess(scope = this)
        advanceUntilIdle()
        isSignOutSuccessDispatched should beTrue(
            message = "SignOutSuccess event should be dispatched"
        )
    }

    @Test
    fun `should send SignOutFailure event`() = runTest {
        var isSignOutFailureDispatched = false
        var error = ""

        reducer.reduce(
            scope = this,
            action = ProfileAction.SignOut,
            authManager = fakeAuthManager,
            dispatchEvent = { event ->
                if (event is ProfileEvent.SignOutError) {
                    isSignOutFailureDispatched = true
                    error = event.error.toString()
                }
            }
        )
        fakeAuthManager.simulateFailure(
            scope = this,
            exception = Exception("test error")
        )
        advanceUntilIdle()

        isSignOutFailureDispatched should beTrue(
            message = "SignOutFailure event should be dispatched"
        )
        error should equal("java.lang.Exception: test error")
    }
}
