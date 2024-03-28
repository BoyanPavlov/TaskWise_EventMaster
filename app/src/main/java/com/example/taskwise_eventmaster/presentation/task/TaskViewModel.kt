package com.example.taskwise_eventmaster.presentation.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import com.example.taskwise_eventmaster.domain.service.authorization.AuthService
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.*
import com.example.taskwise_eventmaster.presentation.utils.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(TaskState())
        private set

    init {
        state = state.copy(
            userData = authService.getSignedInUser()
        )

        loadTasks()
    }

    fun onEvent(event: TaskEvent) {
        when (event) {
            is DeleteTask -> deleteTask(event)

            is SaveTask -> {
                saveTask(event)
                loadTasks()
            }

            is SortTasks -> sortTask(event)

            LoadTasks -> loadTasks()

            is MarkTaskAsDone -> checkTask(event)

            is ChangeLevelOfDifficulty -> changeLevelOfDifficulty(event)

            is EditTask -> {
                editTask(event)
            }
        }
    }

    private fun findTask(taskId: UUID): Task? {
        return state.tasks
            .find { it.id == taskId }
    }

    private fun replaceAndSave(task: Task) {
        viewModelScope.launch {

            findTask(task.id)?.let { element ->
                val tempList = state.tasks.toMutableList()

                tempList.replaceAll {
                    if (it.id == element.id) {
                        return@replaceAll task
                    }
                    return@replaceAll it
                }

                state = state.copy(tasks = tempList)

                repository.saveTask(task)
            }
        }
    }

    private fun editTask(event: EditTask) {
        replaceAndSave(event.task)
    }

    private fun changeLevelOfDifficulty(event: ChangeLevelOfDifficulty) {
        viewModelScope.launch {
            val element = findTask(event.task.id)
                ?.copy(levelOfDifficulty = event.newRating)

            if (element != null) {
                replaceAndSave(element)
            }
        }
    }

    private fun checkTask(event: MarkTaskAsDone) {
        viewModelScope.launch {
            val element = findTask(event.task.id)
                ?.let {
                    it.copy(
                        checkedAsDone = !it.checkedAsDone
                    )
                }

            if (element != null) {
                replaceAndSave(element)
            }
        }
    }

    private fun saveTask(event: SaveTask) {
        viewModelScope.launch(Dispatchers.IO) {
            val task = event.task

            repository.saveTask(task)
        }
    }

    private fun deleteTask(event: DeleteTask) =
        viewModelScope.launch {

            val element = state.tasks.find { task: Task ->
                task.id == event.task.id
            }

            if (element != null) {
                repository.deleteTask(element.id)
                val tempList: List<Task> = state.tasks.minusElement(element)
                state = state.copy(tasks = tempList)
            }
        }

    private fun sortTask(event: SortTasks) {

        val sortedTasks = when (event.sortType) {
            SortType.TITLE_TASK -> state.tasks.sortedBy { it.title }
            SortType.ESTIMATION_TIME -> state.tasks.sortedBy { it.estimationTime }
        }

        state = state.copy(
            sortType = event.sortType,
            tasks = sortedTasks
        )
    }

    private fun loadTasks() {
        viewModelScope.launch {
            val extractedTask = repository.getAllTasks()

            state = state.copy(
                tasks = extractedTask
            )
        }
    }

}