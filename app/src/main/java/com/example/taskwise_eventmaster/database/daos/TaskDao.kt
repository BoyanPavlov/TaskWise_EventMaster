package com.example.taskwise_eventmaster.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.taskwise_eventmaster.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY title ASC")
    fun getTasksOrderedByTitle(): Flow<List<Task>>

    @Query("SELECT * FROM Task ORDER BY goalName ASC")
    fun getTasksOrderedByGoalName(): Flow<List<Task>>

    @Query("SELECT * FROM Task ORDER BY estimationTime ASC")
    fun getTasksOrderedByDate(): Flow<List<Task>>
}