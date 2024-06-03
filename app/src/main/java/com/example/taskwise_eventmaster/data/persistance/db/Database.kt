package com.example.taskwise_eventmaster.data.persistance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskwise_eventmaster.data.persistance.daos.EventDao
import com.example.taskwise_eventmaster.data.persistance.daos.TaskDao
import com.example.taskwise_eventmaster.data.persistance.daos.ThumbnailDao
import com.example.taskwise_eventmaster.data.persistance.model.Event
import com.example.taskwise_eventmaster.data.persistance.model.Task
import com.example.taskwise_eventmaster.data.persistance.model.Thumbnail

@Database(
    entities = [Task::class, Event::class, Thumbnail::class], version = 1
)

@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val eventDao: EventDao
    abstract val thumbnailDao: ThumbnailDao
}
