package com.example.taskwise_eventmaster.presentation.calendar

import com.example.taskwise_eventmaster.domain.model.Task
import java.time.LocalDate

sealed interface CalendarViewEvent {

    data object LoadTasks : CalendarViewEvent

    data class LoadTasksForTheDay(val day: LocalDate) : CalendarViewEvent

    data class EditTask(val task: Task) : CalendarViewEvent

}