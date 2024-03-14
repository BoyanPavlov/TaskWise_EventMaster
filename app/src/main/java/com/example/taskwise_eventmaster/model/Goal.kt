package com.example.taskwise_eventmaster.model

import java.time.LocalDateTime
open class Goal(
    title: String,
    categoryName: String,
    estimationTime: LocalDateTime,
) : Plan(
    title = title,
    categoryName = categoryName,
    estimationTime = estimationTime,
) {

    val tasks = mutableListOf<Task>()
}

//one arm handstand push ->handstand walk-> handstand -> push ups -> push up
