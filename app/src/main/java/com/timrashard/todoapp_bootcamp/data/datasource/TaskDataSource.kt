package com.timrashard.todoapp_bootcamp.data.datasource

import android.icu.text.StringSearch
import com.timrashard.todoapp_bootcamp.data.entity.Task
import com.timrashard.todoapp_bootcamp.data.room.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDataSource(var taskDao: TaskDao) {

    suspend fun getAllTasks() : List<Task> = withContext(Dispatchers.IO){
        return@withContext taskDao.getAllTasks()
    }

    suspend fun save(task: Task){
        taskDao.save(task)
    }

    suspend fun update(task: Task){
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun search(searchText: String) : List<Task>{
        return taskDao.search(searchText)
    }
}