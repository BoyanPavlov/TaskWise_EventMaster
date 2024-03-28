package com.example.taskwise_eventmaster.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskwise_eventmaster.data.daos.TaskDao
import com.example.taskwise_eventmaster.data.model.Task

@Database(
    entities = [Task::class], version = 1
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract val taskDao: TaskDao
}
