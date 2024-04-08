package com.example.taskwise_eventmaster.presentation.home_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import com.example.taskwise_eventmaster.domain.service.authorization.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TaskRepository,
    authService: AuthService
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        state = state.copy(
            userData = authService.getSignedInUser()
        )
        loadTasks()
    }

    fun onEvent(event: HomeScreenEvent) =
        when (event) {
            HomeScreenEvent.LoadTasks -> loadTasks()
        }


    private fun loadTasks() =
        viewModelScope.launch {
            val extractedTask = repository.getAllTasks()

            state = state.copy(
                tasks = extractedTask
            )
        }
}