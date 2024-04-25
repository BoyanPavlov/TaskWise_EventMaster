package com.example.taskwise_eventmaster.domain.model

import java.time.LocalDateTime

data class Event(
    val id: Int,
    val type: String,
    val name: String,
    val dateTime_Utc: LocalDateTime,
    val address: String,
    val city: String,
    val country: String,
    val thumbnailUrl: String// problem here - this should be array, name should be array
    //                          because of multiple performers in one event
)
