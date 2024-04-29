package com.example.taskwise_eventmaster.domain.repository

import com.example.taskwise_eventmaster.domain.model.Event

interface EventRepository {
    suspend fun saveEventLocal(id: Int)

    suspend fun getEventLocal(eventId: Int): Event?

    suspend fun getEventRemote(eventId: Int): Event?

    suspend fun getAllEventsRemote(): List<Event>
    suspend fun getAllEventsLocal(): List<Event>
}