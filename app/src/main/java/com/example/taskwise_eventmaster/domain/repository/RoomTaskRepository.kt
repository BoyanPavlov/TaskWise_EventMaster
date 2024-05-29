package com.example.taskwise_eventmaster.domain.repository

import com.example.taskwise_eventmaster.data.persistance.daos.TaskDao
import com.example.taskwise_eventmaster.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import com.example.taskwise_eventmaster.data.persistance.model.Task as TaskPe

class RoomTaskRepository @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {
    override suspend fun deleteTask(taskId: UUID) = withContext(Dispatchers.IO) {
        try {
            dao.deleteTask(taskId)
        } catch (e: Exception) {
            println("Error during deleting a task")
        }
    }

    override suspend fun saveTask(task: Task) = withContext(Dispatchers.IO) {
        try {
            val taskPe = TaskPe(
                title = task.title,
                estimationTime = task.estimationTime,
                levelOfDifficulty = task.levelOfDifficulty,
                description = task.description,
                id = task.id,
                checkedAsDone = task.checkedAsDone,
                eventId = task.eventId
            )

            dao.upsertTask(taskPe)
        } catch (e: Exception) {
            println("Couldn't upsert a task")
        }
    }

    override suspend fun getTask(taskId: UUID): Task? = withContext(Dispatchers.IO) {
        try {
            val taskPe = dao.getTaskById(taskId)

            taskPe?.let {
                Task(
                    title = it.title,
                    estimationTime = taskPe.estimationTime,
                    levelOfDifficulty = taskPe.levelOfDifficulty,
                    description = taskPe.description,
                    id = taskPe.id,
                    checkedAsDone = taskPe.checkedAsDone,
                    eventId = taskPe.eventId
                )
            }

        } catch (e: Exception) {
            println("Couldn't get a task with this UUID or something else")
            null
        }
    }

    override suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO) {
        dao.getAllTask().map { task ->
            Task(
                title = task.title,
                estimationTime = task.estimationTime,
                levelOfDifficulty = task.levelOfDifficulty,
                description = task.description,
                id = task.id,
                checkedAsDone = task.checkedAsDone,
                eventId = task.eventId
            )
        }
    }
}