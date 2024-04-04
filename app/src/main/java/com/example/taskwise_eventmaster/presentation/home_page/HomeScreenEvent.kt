package com.example.taskwise_eventmaster.presentation.home_page

sealed interface HomeScreenEvent {

    data object LoadTasks : HomeScreenEvent
}