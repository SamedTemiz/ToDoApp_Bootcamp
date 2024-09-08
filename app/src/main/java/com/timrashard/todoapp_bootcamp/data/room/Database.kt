package com.timrashard.todoapp_bootcamp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timrashard.todoapp_bootcamp.data.entity.Task

@Database(entities = [Task::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun getTaskDao() : TaskDao
}