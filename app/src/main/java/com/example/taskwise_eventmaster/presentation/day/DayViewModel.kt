package com.example.taskwise_eventmaster.presentation.day

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDate
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(
    private val repository: TaskRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(DayState())
        private set

    init {
        val argument = savedStateHandle.get<String>("chosenDate").orEmpty()

        if (!argument.isEmpty()) {
            val chosenDay = argument.toLocalDate().toJavaLocalDate()

            loadTasksForTheDay(chosenDay)
        }
    }

    fun onEvent(event: DayEvent) =
        when (event) {
            is DayEvent.EditTask -> editTask(event)
        }

    private fun findTask(taskId: UUID): Task? {
        return state.tasksForTheDay
            .find { it.id == taskId }
    }

    private fun editTask(event: DayEvent.EditTask): Job {
        return viewModelScope.launch {
            findTask(event.task.id)?.let { element ->
                val tempList = state.tasksForTheDay.toMutableList()

                tempList.replaceAll {
                    if (it.id == element.id) {
                        return@replaceAll event.task
                    }
                    return@replaceAll it
                }

                state = state.copy(tasksForTheDay = tempList)

                repository.saveTask(event.task)
            }
        }
    }

    private fun loadTasksForTheDay(chosenDay: LocalDate): Job {
        return viewModelScope.launch {

            val allTasks = repository.getAllTasks()

            state = state.copy(
                tasksForTheDay = allTasks.filter { task: Task ->
                    task.estimationTime.dayOfMonth == chosenDay.dayOfMonth &&
                            task.estimationTime.month == chosenDay.month &&
                            task.estimationTime.year == chosenDay.year
                }
            )
        }
    }


}