package com.example.taskwise_eventmaster.domain.repository

import com.example.taskwise_eventmaster.data.network.datasources.RemoteEventDataSource
import com.example.taskwise_eventmaster.data.persistance.daos.EventDao
import com.example.taskwise_eventmaster.data.persistance.daos.ThumbnailDao
import com.example.taskwise_eventmaster.domain.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import com.example.taskwise_eventmaster.data.persistance.model.Event as EventPe
import com.example.taskwise_eventmaster.data.persistance.model.Thumbnail as ThumbnailPe

class RoomEventRepository @Inject constructor(
    private val dataSource: RemoteEventDataSource,
    private val dao: EventDao,
    private val daoThumbnail: ThumbnailDao
) : EventRepository {
    override suspend fun saveEventLocal(event: Event) =
        withContext(Dispatchers.IO) {
            try {

                val eventPe = EventPe(
                    id = event.id,
                    type = event.type,
                    name = event.name,
                    dateTimeUtc = event.dateTimeUtc,
                    address = event.address,
                    city = event.city,
                    country = event.country,
                )

                dao.upsertEvent(eventPe)

                for (thumbnail in event.thumbnails) {

                    val thumbnailPe = ThumbnailPe(
                        thumbnailUrl = thumbnail.thumbnailUrl,
                        eventId = event.id,
                    )

                    daoThumbnail.upsertThumbnail(thumbnailPe)
                }
            } catch (e: Exception) {
                println("Couldn't upsert an event")
            }
        }

    override suspend fun deleteEventLocal(eventId: Int) {
        daoThumbnail.deleteThumbnailsFromEvent(eventId)
        dao.deleteEvent(eventId)
    }

    override suspend fun getEventRemote(eventId: Int): Event? =
        withContext(Dispatchers.IO) {
            try {

                val eventNe = dataSource.getEvent(eventId)

                val thumbnails = getThumbnails(eventId)

                eventNe?.let {

                    Event(
                        id = eventNe.id,
                        type = eventNe.type,
                        name = eventNe.venue.name,
                        dateTimeUtc = eventNe.dataTimeUtc.toLocalDateTime()
                            .toJavaLocalDateTime(),
                        address = eventNe.venue.address ?: "address: unspecified",
                        city = eventNe.venue.city ?: " city: unspecified",
                        country = eventNe.venue.country ?: " country: unspecified",
                        thumbnails = thumbnails
                    )
                }

            } catch (e: Exception) {
                println("Couldn't get an event with this ID, remote")
                null
            }
        }

    override suspend fun getEventLocal(eventId: Int): Event? =
        withContext(Dispatchers.IO) {
            val eventPe = dao.getEventById(eventId)

            if (eventPe != null) {

                val thumbnails = getThumbnails(eventId)

                Event(
                    id = eventPe.id,
                    type = eventPe.type,
                    name = eventPe.name,
                    dateTimeUtc = eventPe.dateTimeUtc,
                    address = eventPe.address,
                    city = eventPe.city,
                    country = eventPe.country,
                    thumbnails = thumbnails
                )

            } else {
                println("Couldn't get an event with this ID, local")
                null
            }
        }

    override suspend fun getAllEventsRemote(): List<Event> =
        withContext(Dispatchers.IO) {
            val collectionOfEventsNe = dataSource.getEvents()

            collectionOfEventsNe?.events?.map { event ->

                val thumbnails = mutableListOf<Event.Thumbnail>()

                for (performer in event.performers) {
                    if (performer.image != null) {
                        thumbnails.add(Event.Thumbnail(performer.image))
                    }
                }

                Event(
                    id = event.id,
                    type = event.type,
                    name = event.venue.name,
                    dateTimeUtc = event.dataTimeUtc.toLocalDateTime().toJavaLocalDateTime(),
                    address = event.venue.address ?: "address: unspecified",
                    city = event.venue.city ?: " city: unspecified",
                    country = event.venue.country ?: " country: unspecified",
                    thumbnails = thumbnails
                )
            }

                ?: emptyList()
        }

    override suspend fun getAllEventsLocal(): List<Event> =
        withContext(Dispatchers.IO) {
            val collectionOfEventsPe = dao.getAllEvents()

            collectionOfEventsPe.map { event ->

                val thumbnails = getThumbnails(event.id)

                Event(
                    id = event.id,
                    type = event.type,
                    name = event.name,
                    dateTimeUtc = event.dateTimeUtc,
                    address = event.address,
                    city = event.city,
                    country = event.country,
                    thumbnails = thumbnails
                )
            }
        }

    private fun getThumbnails(eventId: Int): List<Event.Thumbnail> {
        val thumbnails = mutableListOf<Event.Thumbnail>()

        val thumbnailsPe = daoThumbnail.getAllThumbnailsForEvent(eventId)

        for (thumbnail in thumbnailsPe) {
            thumbnails += Event.Thumbnail(thumbnailUrl = thumbnail.thumbnailUrl)
        }

        return thumbnails
    }
}