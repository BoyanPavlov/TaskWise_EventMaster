package com.example.taskwise_eventmaster.presentation.logic_elements
import kotlinx.datetime.LocalDateTime

class Task (
    title: String,
    categoryName: String,
    estimationTime: LocalDateTime,
    val levelOfHardness: Int,
    val description: String = ""
): Goal<Any>(
    title = title,
    categoryName = categoryName,
    estimationTime = estimationTime,
)
