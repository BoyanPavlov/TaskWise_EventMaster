package com.example.taskwise_eventmaster.presentation.profile

sealed interface ProfileScreenEvent {
    data object OnSignOut : ProfileScreenEvent
}