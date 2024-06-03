package com.example.taskwise_eventmaster.presentation.task

import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.presentation.utils.SortType

sealed interface TaskEvent {

    data class SaveTask(val task: Task) : TaskEvent

    data class SortTasks(val sortType: SortType) : TaskEvent

    data class DeleteTask(val task: Task) : TaskEvent

    data class MarkTaskAsDone(val task: Task) : TaskEvent

    data class ChangeLevelOfDifficulty(val task: Task, val newRating: Int) : TaskEvent

    data object LoadTasks : TaskEvent

    data class EditTask(val task: Task) : TaskEvent

}
