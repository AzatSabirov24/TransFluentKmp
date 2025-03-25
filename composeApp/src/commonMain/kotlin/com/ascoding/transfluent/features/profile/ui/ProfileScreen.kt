package com.ascoding.transfluent.features.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ascoding.transfluent.features.profile.ProfileAction
import com.ascoding.transfluent.features.profile.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreenRoot(
    viewModel: ProfileViewModel = koinViewModel()
) {
    ProfileScreen(viewModel::onAction)

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            println("xyz ProfileScreen.kt -> ProfileScreenRoot -> ${event}")
        }
    }
}

@Composable
fun ProfileScreen(onAction: (ProfileAction) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            content = { Text("Sign out") },
            onClick = { onAction(ProfileAction.SignOut) }
        )
    }
}