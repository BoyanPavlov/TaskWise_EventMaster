package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskwise_eventmaster.domain.service.authorization.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    authService: AuthService
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        state = state.copy(
            userData = authService.getSignedInUser()
        )
    }
}