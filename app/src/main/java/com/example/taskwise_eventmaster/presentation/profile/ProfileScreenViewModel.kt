package com.example.taskwise_eventmaster.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.service.authorization.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(ProfileScreenState())
        private set

    init {
        state = state.copy(
            userData = authService.getSignedInUser()
        )
    }

    fun onEvent(event: ProfileScreenEvent) = when (event) {
        is ProfileScreenEvent.OnSignOut -> signOut()
    }

    private fun signOut() {
        viewModelScope.launch {
            authService.signOut()
        }
    }
}
