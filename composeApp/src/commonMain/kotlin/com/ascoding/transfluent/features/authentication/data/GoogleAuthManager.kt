package com.ascoding.transfluent.features.authentication.data

import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.authentication.domain.Credentials
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GoogleAuthManager : AuthManager {

    override val userAuthId = Firebase.auth.currentUser?.uid

    override fun register(
        scope: CoroutineScope,
        credentials: Credentials,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

    }

    override fun login(
        scope: CoroutineScope,
        credentials: Credentials?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        scope.launch {
            try {
                GoogleAuthProvider.create(
                    credentials = GoogleAuthCredentials(
                        serverId = "835330334494-4b9qteje03qamq5i3mmrbjhd0lln357e.apps.googleusercontent.com"
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    override fun signOut(
        scope: CoroutineScope,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
