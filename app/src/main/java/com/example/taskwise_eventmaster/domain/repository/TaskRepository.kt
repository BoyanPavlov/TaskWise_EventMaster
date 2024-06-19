package com.example.taskwise_eventmaster.domain.repository

import com.example.taskwise_eventmaster.domain.model.Task
import java.util.UUID

interface TaskRepository {
    suspend fun deleteTask(taskId: UUID)

    suspend fun saveTask(task: Task)

    suspend fun getTask(taskId: UUID): Task?

    suspend fun getTaskByEventID(eventId: Int?): Task?

    suspend fun getAllTasks(): List<Task>
}