package com.example.taskwise_eventmaster.data.network.datasources

import com.example.taskwise_eventmaster.data.network.model.EventResponse
import com.example.taskwise_eventmaster.data.network.model.Event

interface RemoteEventDataSource {
    suspend fun getEvents(): EventResponse?

    suspend fun getEvent(eventId: Int): Event?
}