package com.example.taskwise_eventmaster.presentation.tasks

import com.example.taskwise_eventmaster.presentation.goals.Goal
import kotlinx.datetime.LocalDateTime

class Task(
    title: String,
    categoryName: String,
    estimationTime: LocalDateTime,
    val levelOfHardness: Int,
    goalName: String = String(),
    val description: String = ""
) : Goal<Any>(
    title = title,
    categoryName = categoryName,
    goalName = goalName,
    estimationTime = estimationTime,
)
