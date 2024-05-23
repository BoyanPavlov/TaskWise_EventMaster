package com.example.taskwise_eventmaster.data.persistance.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.taskwise_eventmaster.data.persistance.model.Thumbnail
@Dao
interface ThumbnailDao {
    @Upsert
    suspend fun upsertThumbnail(thumbnail: Thumbnail)

    @Query("SELECT * FROM Thumbnail WHERE eventId =:eventId")
    fun getAllThumbnailsForThisEvent(eventId: Int): List<Thumbnail>

    @Query("SELECT * FROM Thumbnail WHERE eventId =:eventId LIMIT 1")
    fun getFirstThumbnailForEvent(eventId: Int): Thumbnail

    @Query("DELETE FROM Task WHERE eventId =:eventId")
    suspend fun deleteThumbnailsFromEvent(eventId: Int)
}