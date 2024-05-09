package com.example.taskwise_eventmaster.data.persistance.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class Task(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val estimationTime: LocalDateTime,
    val levelOfDifficulty: Int,
    val goalName: String = "",
    val description: String,
    var checkedAsDone: Boolean = false
)
