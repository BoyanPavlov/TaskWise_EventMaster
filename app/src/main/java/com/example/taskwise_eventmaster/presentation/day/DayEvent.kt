package com.example.taskwise_eventmaster.presentation.day

import com.example.taskwise_eventmaster.domain.model.Task

sealed interface DayEvent {
    data class EditTask(val task: Task) : DayEvent

}