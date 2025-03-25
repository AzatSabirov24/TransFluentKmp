package com.ascoding.transfluent.features.profile.presentation.reducer

import com.ascoding.transfluent.features.profile.ProfileEvent
import com.ascoding.transfluent.features.profile.ProfileState
import com.ascoding.transfluent.features.profile.reducer.ProfileEventReducer
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.should
import kotlin.test.Test

class ProfileEventReducerTest {

    private val reducer = ProfileEventReducer()

    @Test
    fun `should set isSignedOut true when event is SignOutSuccess`() {
        val state = reducer.reduce(
            event = ProfileEvent.SignOutSuccess,
            state = ProfileState()
        )

        state.isSignedOut should beTrue()
    }

    @Test
    fun `should set isSignedOut false when event is SignOutError`() {
        val state = reducer.reduce(
            event = ProfileEvent.SignOutError(error = "test error"),
            state = ProfileState()
        )

        state.isSignedOut should beFalse()
    }
}
