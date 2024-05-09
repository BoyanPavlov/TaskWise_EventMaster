package com.example.taskwise_eventmaster.presentation.day

import com.example.taskwise_eventmaster.domain.model.Task

data class DayState(
    val tasksForTheDay: List<Task> = emptyList()
)
