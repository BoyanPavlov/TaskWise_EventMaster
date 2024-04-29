package com.example.taskwise_eventmaster.data.network.datasources

import com.example.taskwise_eventmaster.data.network.model.EventResponse

interface RemoteEventDataSource {
    suspend fun getEvents(): EventResponse?
}