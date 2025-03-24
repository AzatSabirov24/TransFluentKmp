package com.ascoding.transfluent.features.authentication.domain

data class Credentials(
    val idToken: String = "",
    val accessToken: String = "",
    val email: String = "",
    val password: String = "",
)
