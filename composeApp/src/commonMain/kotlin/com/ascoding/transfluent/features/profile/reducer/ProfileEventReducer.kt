package com.ascoding.transfluent.features.profile.reducer

import com.ascoding.transfluent.features.profile.ProfileEvent
import com.ascoding.transfluent.features.profile.ProfileState

class ProfileEventReducer {

    fun reduce(event: ProfileEvent, state: ProfileState): ProfileState {
        return when (event) {
            is ProfileEvent.SignOutSuccess -> state.copy(isSignedOut = true)
            is ProfileEvent.SignOutError -> state.copy(isSignedOut = false)
        }
    }
}