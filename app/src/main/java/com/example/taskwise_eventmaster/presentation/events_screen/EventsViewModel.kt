package com.example.taskwise_eventmaster.presentation.events_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.adapters.eventToTask
import com.example.taskwise_eventmaster.domain.repository.EventRepository
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repositoryEvent: EventRepository,
    private val repositoryTask: TaskRepository,
) : ViewModel() {
    var state by mutableStateOf(EventsState())
        private set

    init {
        fetchEventInfo()
    }

    fun onEvent(event: EventsScreenEvent) {
        when (event) {
            is EventsScreenEvent.SaveEventInCalendar -> saveEventInCalendar(event)
        }
    }

    private fun saveEventInCalendar(event: EventsScreenEvent.SaveEventInCalendar) {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = repositoryTask.getAllTasks()

            val eventAlreadyAdded =
                (tasks.find { task -> task.eventId == event.event.id } == null)

            if (eventAlreadyAdded) {
                repositoryEvent.saveEventLocal(event.event)
                repositoryTask.saveTask(eventToTask(event.event))
            }
        }
    }

    private fun fetchEventInfo() {

        viewModelScope.launch {
            val extractedEvents = repositoryEvent.getAllEventsRemote()

            state = state.copy(
                events = extractedEvents
            )
        }
    }
}