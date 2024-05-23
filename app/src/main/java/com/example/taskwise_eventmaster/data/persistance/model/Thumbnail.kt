package com.example.taskwise_eventmaster.data.persistance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "Thumbnail",
    foreignKeys = [
        ForeignKey(
            entity = Event::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("eventId"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["thumbnailUrl"], unique = true)]
)

data class Thumbnail(
    @PrimaryKey
    val thumbnailId: UUID = UUID.randomUUID(),
    val thumbnailUrl: String,
    val eventId: Int
)