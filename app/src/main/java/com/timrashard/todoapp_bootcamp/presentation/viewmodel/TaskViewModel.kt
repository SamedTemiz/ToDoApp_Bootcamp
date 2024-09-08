package com.timrashard.todoapp_bootcamp.presentation.viewmodel

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
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    fun saveTask(task: Task){
        CoroutineScope(Dispatchers.Main).launch {
            repository.save(task)
        }
    }

    fun updateTask(task: Task){
        CoroutineScope(Dispatchers.Main).launch {
            repository.update(task)
        }
    }
}