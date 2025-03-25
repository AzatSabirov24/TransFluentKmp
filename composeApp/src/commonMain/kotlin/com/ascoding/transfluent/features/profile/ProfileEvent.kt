package com.ascoding.transfluent.features.profile

sealed interface ProfileEvent {

    data object SignOutSuccess : ProfileEvent

    data class SignOutError(val error: String?) : ProfileEvent
}