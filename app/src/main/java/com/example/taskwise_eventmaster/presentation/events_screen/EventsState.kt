package com.example.taskwise_eventmaster.presentation.events_screen

import com.example.taskwise_eventmaster.domain.model.Event

data class EventsState(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = true
)
