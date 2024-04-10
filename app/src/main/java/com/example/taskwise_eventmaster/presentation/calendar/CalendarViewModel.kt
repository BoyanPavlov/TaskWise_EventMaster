package com.example.taskwise_eventmaster.presentation.calendar


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {

    var state by mutableStateOf(CalendarViewState())
        private set

    init {
        loadTasks()
    }

    fun onEvent(event: CalendarViewEvent) =
        when (event) {
            CalendarViewEvent.LoadTasks -> loadTasks()
        }


    private fun loadTasks() =
        viewModelScope.launch {
            val extractedTask = repository.getAllTasks()

            state = state.copy(
                tasks = extractedTask
            )
        }
}