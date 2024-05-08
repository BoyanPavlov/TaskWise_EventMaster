package com.example.taskwise_eventmaster.data.persistance.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Event(
    @PrimaryKey
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

