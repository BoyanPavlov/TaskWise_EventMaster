package com.example.taskwise_eventmaster.presentation.home_page

import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.presentation.sign_in.UserData

data class HomeScreenState(
    val userData: UserData? = null,
    val tasks: List<Task> = emptyList()
)