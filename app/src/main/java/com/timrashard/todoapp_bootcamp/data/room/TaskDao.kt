package com.timrashard.todoapp_bootcamp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.timrashard.todoapp_bootcamp.data.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM toDos")
    suspend fun getAllTasks(): List<Task>

    @Insert
    suspend fun save(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM toDos WHERE task_title LIKE '%' || :searchText || '%'")
    suspend fun search(searchText: String): List<Task>
}