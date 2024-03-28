package com.example.taskwise_eventmaster.presentation.task

import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.presentation.sign_in.UserData
import com.example.taskwise_eventmaster.presentation.utils.SortType

data class TaskState(
    val userData: UserData? = null,
    val sortType: SortType = SortType.TITLE_TASK,

    val tasks: List<Task> = emptyList(),

    val task: Task? = null
)