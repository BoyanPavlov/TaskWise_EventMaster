package com.example.taskwise_eventmaster.presentation.events_screen

import com.example.taskwise_eventmaster.domain.model.Event

sealed interface EventsScreenEvent {

    data class SaveEventInCalendar(val concert: Event) : EventsScreenEvent

    data object ReloadEvents : EventsScreenEvent

}