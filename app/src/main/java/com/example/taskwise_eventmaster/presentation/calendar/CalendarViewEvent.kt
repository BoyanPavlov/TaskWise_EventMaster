package com.example.taskwise_eventmaster.presentation.calendar

sealed interface CalendarViewEvent {

    data object LoadTasks : CalendarViewEvent

}