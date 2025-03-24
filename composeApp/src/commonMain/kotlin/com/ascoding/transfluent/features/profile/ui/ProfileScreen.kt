package com.ascoding.transfluent.features.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ascoding.transfluent.features.profile.ProfileEvent
import com.ascoding.transfluent.features.profile.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreenRoot(
    viewModel: ProfileViewModel = koinViewModel()
) {
    ProfileScreen(viewModel::onEvent)
}

@Composable
fun ProfileScreen(onEvent: (ProfileEvent) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            content = { Text("Sign out") },
            onClick = { onEvent(ProfileEvent.SignOut) }
        )
    }
}