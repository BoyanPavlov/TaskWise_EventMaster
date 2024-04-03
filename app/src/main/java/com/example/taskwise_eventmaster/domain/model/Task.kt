package com.example.taskwise_eventmaster.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val title: String = "",
    val estimationTime: LocalDateTime = LocalDateTime.now(),
    val levelOfDifficulty: Int = 0,
    val description: String = "",
    val checkedAsDone: Boolean = false
)
