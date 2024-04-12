package com.example.taskwise_eventmaster.presentation.calendar


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwise_eventmaster.domain.model.Task
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {

    var state by mutableStateOf(CalendarViewState())
        private set

    init {
        loadTasks()
    }

    fun onEvent(event: CalendarViewEvent) =
        when (event) {
            CalendarViewEvent.LoadTasks -> loadTasks()
            is CalendarViewEvent.LoadTasksForTheDay -> loadTasksForTheDay(event)
            is CalendarViewEvent.EditTask -> editTask(event)
        }


    private fun findTask(taskId: UUID): Task? {
        return state.tasks
            .find { it.id == taskId }
    }

    private fun editTask(event: CalendarViewEvent.EditTask): Job {
        return viewModelScope.launch {
            findTask(event.task.id)?.let { element ->
                val tempList = state.tasks.toMutableList()

                tempList.replaceAll {
                    if (it.id == element.id) {
                        return@replaceAll event.task
                    }
                    return@replaceAll it
                }

                state = state.copy(tasks = tempList)

                repository.saveTask(event.task)
            }
        }
    }

    private fun loadTasksForTheDay(event: CalendarViewEvent.LoadTasksForTheDay): Job {
        return viewModelScope.launch {
            state = state.copy(
                tasksForTheDay = state.tasks.filter { task: Task ->
                    task.estimationTime.dayOfMonth == event.day.dayOfMonth &&
                            task.estimationTime.month == event.day.month &&
                            task.estimationTime.year == event.day.year
                }
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