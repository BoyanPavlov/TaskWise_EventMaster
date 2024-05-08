package com.example.taskwise_eventmaster.data.persistance.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.taskwise_eventmaster.data.persistance.model.Event

@Dao
interface EventDao {
    @Upsert
    suspend fun upsertEvent(event: Event)

    @Query("DELETE FROM Event WHERE id=:eventId")
    suspend fun deleteEvent(eventId: Int)

    @Query("SELECT * FROM Event WHERE id=:eventId")
    fun getEventById(eventId: Int): Event?

    @Query("SELECT * FROM Event")
    fun getAllEventsLocal(): List<Event>
}