package com.ascoding.transfluent.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ascoding.transfluent.features.authentication.domain.AuthManager
import com.ascoding.transfluent.features.profile.reducer.ProfileActionReducer
import com.ascoding.transfluent.features.profile.reducer.ProfileEventReducer
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val actionReducer: ProfileActionReducer,
    private val eventReducer: ProfileEventReducer,
    private val authManager: AuthManager,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<ProfileEvent>()
    val events = _events.asSharedFlow()

    fun onAction(action: ProfileAction) {
        viewModelScope.launch {
            actionReducer.reduce(
                scope = viewModelScope,
                action = action,
                authManager = authManager,
                dispatchEvent = { event ->
                    onEvent(event)
                }
            )
        }
    }

    fun onEvent(event: ProfileEvent) {
        viewModelScope.launch {
            _state.update {
                eventReducer.reduce(
                    event = event,
                    state = state.value
                )
            }
            _events.emit(event)
        }
    }
}
