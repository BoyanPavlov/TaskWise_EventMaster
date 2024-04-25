package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: EventRepository,
) : ViewModel() {
    var state by mutableStateOf(EventsState())
        private set

    init {
        fetchEventInfo()
    }

    fun onEvent(event: EventsScreenEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun fetchEventInfo() {
        viewModelScope.launch {
            val extractedEvents = repository.getAllEvents()

            state = state.copy(
                events = extractedEvents
            )
        }
    }
}