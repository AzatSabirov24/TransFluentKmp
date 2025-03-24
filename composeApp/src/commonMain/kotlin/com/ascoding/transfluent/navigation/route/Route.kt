package com.ascoding.transfluent.navigation.route

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object BackStack: Route

    // Auth
    @Serializable
    data object AuthGraph: Route

    @Serializable
    data object Splash: Route

    @Serializable
    data object Welcome: Route

    @Serializable
    data object Register: Route

    @Serializable
    data object Login: Route

    //Main
    @Serializable
    data object MainGraph: Route

    @Serializable
    data object Translator: Route

    @Serializable
    data object Profile: Route
}