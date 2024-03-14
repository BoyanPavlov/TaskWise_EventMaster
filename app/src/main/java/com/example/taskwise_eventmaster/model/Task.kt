package com.example.taskwise_eventmaster.model

import java.time.LocalDateTime

class Task (
    title: String,
    categoryName: String,
    estimationTime: LocalDateTime,
    val levelOfHardness: Int,
    val goalName: String = String(),
    val description: String = ""
) : Plan(
    title = title,
    categoryName = categoryName,
    estimationTime = estimationTime,
)
