package com.example.taskwise_eventmaster.domain.adapters

import com.example.taskwise_eventmaster.domain.model.Event
import com.example.taskwise_eventmaster.domain.model.Task


fun eventToTask(event: Event) =
    Task(
        eventId = event.id,
        title = event.name,
        estimationTime = event.dateTimeUtc,
        levelOfDifficulty = 1,
        description = "${event.address}, ${event.city}, ${event.country}",
    )
