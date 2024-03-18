package com.example.taskwise_eventmaster.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.Goalwise_eventmaster.database.daos.GoalDao
import com.example.taskwise_eventmaster.database.daos.CategoryDao
import com.example.taskwise_eventmaster.database.daos.TaskDao
import com.example.taskwise_eventmaster.model.Goal
import com.example.taskwise_eventmaster.model.Task
import com.example.taskwise_eventmaster.model.categories.GoalCategory

@Database(
    entities = [Task::class, Goal::class, GoalCategory::class],
    version = 1
)
abstract class PlanningViewDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val goalDao: GoalDao
    abstract val categoryDao: CategoryDao
}