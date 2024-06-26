package com.example.taskwise_eventmaster.data.persistance.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.taskwise_eventmaster.data.persistance.model.Task
import java.util.UUID

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(task: Task)

    @Query("DELETE FROM Task WHERE id=:taskId")
    suspend fun deleteTask(taskId: UUID)

    @Query("SELECT * FROM Task WHERE id=:taskId")
    fun getTaskById(taskId: UUID): Task?

    @Query("SELECT * FROM Task WHERE eventId=:eventId")
    fun getTaskByEventId(eventId: Int): Task?

    @Query("SELECT * FROM Task")
    fun getAllTask(): List<Task>
}