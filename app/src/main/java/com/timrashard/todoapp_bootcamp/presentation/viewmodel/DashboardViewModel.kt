package com.timrashard.todoapp_bootcamp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timrashard.todoapp_bootcamp.data.entity.Task
import com.timrashard.todoapp_bootcamp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private var taskRepository: TaskRepository
) : ViewModel() {

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> = _taskList

    fun getAllTasks() {
        CoroutineScope(Dispatchers.Main).launch {
            _taskList.value = taskRepository.getAllTasks()
        }
    }

    fun deleteTask(task: Task){
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.delete(task)
            getAllTasks()
        }
    }

    fun searchTask(searchText: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _taskList.value = taskRepository.search(searchText)
        }
    }

    fun taskUpdate(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.update(task)
        }
    }
}