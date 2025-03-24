package com.ascoding.transfluent.features.authorization.authentication.data

import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.authentication.domain.Credentials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FakeAuthManager(override val userAuthId: String?) : AuthManager {

    private var onSuccess: (() -> Unit)? = null
    private var onFailure: ((Exception) -> Unit)? = null

    override fun register(
        scope: CoroutineScope,
        credentials: Credentials,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        this.onSuccess = {
            onSuccess()
        }
        this.onFailure = { exception ->
            onFailure(exception)
        }
    }

    override fun login(
        scope: CoroutineScope,
        credentials: Credentials,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        this.onSuccess = {
            onSuccess()
        }
        this.onFailure = { exception ->
            onFailure(exception)
        }
    }

    fun simulateSuccess(scope: CoroutineScope) {
        scope.launch {
            onSuccess?.invoke()
        }
    }

    fun simulateFailure(
        scope: CoroutineScope,
        exception: Exception
    ) {
        scope.launch {
            onFailure?.invoke(exception)
        }
    }
}
