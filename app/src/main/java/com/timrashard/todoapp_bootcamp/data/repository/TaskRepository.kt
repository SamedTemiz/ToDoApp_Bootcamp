package com.timrashard.todoapp_bootcamp.data.repository

import com.timrashard.todoapp_bootcamp.data.datasource.TaskDataSource
import com.timrashard.todoapp_bootcamp.data.entity.Task

class TaskRepository(var taskDataSource: TaskDataSource) {

    suspend fun getAllTasks() : List<Task> = taskDataSource.getAllTasks()

    suspend fun save(task: Task) = taskDataSource.save(task)

    suspend fun update(task: Task) = taskDataSource.update(task)

    suspend fun delete(task: Task) = taskDataSource.delete(task)

    suspend fun search(searchText: String) : List<Task> = taskDataSource.search(searchText)
}