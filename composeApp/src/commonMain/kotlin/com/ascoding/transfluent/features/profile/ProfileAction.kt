package com.ascoding.transfluent.features.profile

sealed interface ProfileAction {

    data object SignOut : ProfileAction
}