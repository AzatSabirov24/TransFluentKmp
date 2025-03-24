package com.ascoding.transfluent.features.profile

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class ProfileReducer {

    suspend fun reduce(state: ProfileState, event: ProfileEvent): ProfileState {
        when (event) {
            is ProfileEvent.SignOut -> {
                Firebase.auth.signOut()
                return state.copy(isSignedOut = false)
            }
        }
    }
}