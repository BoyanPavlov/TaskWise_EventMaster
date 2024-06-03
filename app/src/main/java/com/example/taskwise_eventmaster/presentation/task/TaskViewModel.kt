package com.example.taskwise_eventmaster.presentation.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import com.example.taskwise_eventmaster.domain.service.authorization.AuthService
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.ChangeLevelOfDifficulty
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.DeleteTask
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.EditTask
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.LoadTasks
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.MarkTaskAsDone
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.SaveTask
import com.example.taskwise_eventmaster.presentation.task.TaskEvent.SortTasks
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

        loadTasks().invokeOnCompletion {
            sortTask(state.sortType)
        }
    }

    fun onEvent(event: TaskEvent) {
        when (event) {
            is DeleteTask -> deleteTask(event.task)

            is SaveTask -> {
                saveTask(event.task).invokeOnCompletion {
                    loadTasks().invokeOnCompletion {
                        sortTask(state.sortType)
                    }
                }
            }

            is SortTasks -> sortTask(event.sortType)

            LoadTasks -> loadTasks()

            is MarkTaskAsDone -> checkTask(event.task)

            is ChangeLevelOfDifficulty -> changeLevelOfDifficulty(
                task = event.task,
                newRating = event.newRating
            )

            is EditTask -> {
                editTask(event.task).invokeOnCompletion {
                    sortTask(state.sortType)
                }
            }
        }
    }

    private fun findTask(taskId: UUID): Task? {
        return state.tasks
            .find { it.id == taskId }
    }

    private fun replaceAndSave(task: Task) =
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


    private fun editTask(task: Task) =
        replaceAndSave(task)


    private fun changeLevelOfDifficulty(task: Task, newRating: Int) {
        viewModelScope.launch {
            val element = findTask(task.id)
                ?.copy(levelOfDifficulty = newRating)

            if (element != null) {
                replaceAndSave(element)
            }
        }
    }

    private fun checkTask(task: Task) {
        viewModelScope.launch {
            val element = findTask(task.id)
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

    private fun saveTask(task: Task) =
        viewModelScope.launch(Dispatchers.IO) {

            repository.saveTask(task)
        }

    private fun deleteTask(task: Task) =
        viewModelScope.launch {

            repository.deleteTask(task.id)
            val tempList: List<Task> = state.tasks.minusElement(task)
            state = state.copy(tasks = tempList)
        }

    private fun sortTask(sortType: SortType) {
        viewModelScope.launch {
            val sortedTasks = when (sortType) {
                SortType.TITLE_TASK -> state.tasks.sortedBy { it.title }
                SortType.ESTIMATION_TIME -> state.tasks.sortedBy { it.estimationTime }
            }

            state = state.copy(
                sortType = sortType,
                tasks = sortedTasks
            )
        }
    }

    private fun loadTasks() =
        viewModelScope.launch {
            val extractedTask = repository.getAllTasks()

            state = state.copy(
                tasks = extractedTask
            )
        }
}