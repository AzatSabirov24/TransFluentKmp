package com.ascoding.transfluent.features.authentication.domain

import kotlinx.coroutines.CoroutineScope

interface AuthManager {

    val userAuthId: String?

    fun register(
        scope: CoroutineScope,
        credentials: Credentials,
        onSuccess: () -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    )

    fun login(
        scope: CoroutineScope,
        credentials: Credentials?,
        onSuccess: () -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    )
}
