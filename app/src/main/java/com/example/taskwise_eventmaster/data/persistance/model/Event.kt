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
    val dateTimeUtc: LocalDateTime,
    val address: String,
    val city: String,
    val country: String,
)

