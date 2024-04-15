package com.example.taskwise_eventmaster.presentation.calendar

import com.example.taskwise_eventmaster.domain.model.Task

data class CalendarViewState(
    val tasks: List<Task> = emptyList(),
)