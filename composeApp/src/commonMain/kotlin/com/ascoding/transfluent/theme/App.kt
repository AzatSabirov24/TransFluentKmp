package com.ascoding.transfluent.theme

import androidx.compose.runtime.Composable
import com.ascoding.transfluent.navigation.AppNavigation

@Composable
internal fun App() = AppTheme {

//    var authReady by remember { mutableStateOf(false) }
//    LaunchedEffect(Unit) {
//        val googleAuthProvider = GoogleAuthProvider.create(
//            credentials = GoogleAuthCredentials(
//                serverId = "835330334494-4b9qteje03qamq5i3mmrbjhd0lln357e.apps.googleusercontent.com"
//            )
//        )
//    }
//        authReady = true
//
//
//    println("xyz App.kt -> App  authReady-> ${authReady}")
    AppNavigation()
}