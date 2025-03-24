package com.ascoding.transfluent.features.profile

sealed interface ProfileEvent {

    data object SignOut : ProfileEvent
}