package com.ascoding.transfluent.features.profile.reducer

import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.profile.ProfileAction
import com.ascoding.transfluent.features.profile.ProfileEvent
import kotlinx.coroutines.CoroutineScope

class ProfileActionReducer {

    fun reduce(
        scope: CoroutineScope,
        action: ProfileAction,
        authManager: AuthManager?,
        dispatchEvent: (ProfileEvent) -> Unit
    ) {
        when (action) {
            is ProfileAction.SignOut -> {
                authManager?.signOut(
                    scope = scope,
                    onSuccess = {
                        dispatchEvent(ProfileEvent.SignOutSuccess)
                    },
                    onFailure = { e ->
                        dispatchEvent(ProfileEvent.SignOutError(error = e.toString()))
                    }
                )
            }
        }
    }
}
