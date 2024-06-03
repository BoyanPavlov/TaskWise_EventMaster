package com.example.taskwise_eventmaster.data.persistance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(
    tableName = "Task",
    foreignKeys = [
        ForeignKey(
            entity = Event::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("eventId"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["eventId"], unique = true)]
)

data class Task(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val estimationTime: LocalDateTime,
    val levelOfDifficulty: Int,
    val goalName: String = "",
    val description: String,
    var checkedAsDone: Boolean = false,
    val eventId: Int? = null
)
