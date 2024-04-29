package com.example.taskwise_eventmaster.domain.repository

import com.example.taskwise_eventmaster.data.network.datasources.RemoteEventDataSource
import com.example.taskwise_eventmaster.domain.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class OkHttpEventRepository @Inject constructor(
    private val dataSource: RemoteEventDataSource
) : EventRepository {
    override suspend fun saveEventLocal(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getEventRemote(eventId: Int): Event? = withContext(Dispatchers.IO) {
        try {
            val eventPe = dataSource.getEvents()?.events?.find { event -> event.id == eventId }

            eventPe?.let {
                Event(
                    id = eventPe.id,
                    type = eventPe.type,
                    name = eventPe.venue.name,
                    dateTime_Utc = eventPe.datetime_utc.toLocalDateTime().toJavaLocalDateTime(),
                    address = eventPe.venue.address,
                    city = eventPe.venue.city,
                    country = eventPe.venue.country,
                    thumbnailUrl = eventPe.performers.first().image
                )
            }

        } catch (e: Exception) {
            println("Couldn't get an event with this ID")
            null
        }
    }

    override suspend fun getEventLocal(eventId: Int): Event? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEventsRemote(): List<Event> = withContext(Dispatchers.IO) {
        val collectionOfEvents = dataSource.getEvents()
        collectionOfEvents?.events?.map { event ->
            Event(
                id = event.id,
                type = event.type,
                name = event.venue.name,
                dateTime_Utc = event.datetime_utc.toLocalDateTime().toJavaLocalDateTime(),
                address = event.venue.address,
                city = event.venue.city,
                country = event.venue.country,
                thumbnailUrl = event.performers.first().image
            )
        }
            ?: emptyList()
    }

    override suspend fun getAllEventsLocal(): List<Event> {
        TODO("Not yet implemented")
    }
}