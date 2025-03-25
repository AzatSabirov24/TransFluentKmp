package com.ascoding.transfluent.features.authentication.data

import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.authentication.domain.Credentials
import dev.gitlive.firebase.Firebase
//import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FirebaseAuthManager : AuthManager {

    override val userAuthId = Firebase.auth.currentUser?.uid

    override fun register(
        scope: CoroutineScope,
        credentials: Credentials,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        scope.launch {
            try {
                val auth = Firebase.auth
                auth.createUserWithEmailAndPassword(
                    email = credentials.email,
                    password = credentials.password
                )
                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }

    override fun login(
        scope: CoroutineScope,
        credentials: Credentials?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        scope.launch {
            try {
                val auth = Firebase.auth
                auth.signInWithEmailAndPassword(
                    email = credentials?.email ?: "",
                    password = credentials?.password ?: ""
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
        scope.launch {
            try {
                val auth = Firebase.auth
                auth.signOut()
                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
}