package com.timrashard.todoapp_bootcamp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "toDos")
data class Task(

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var task_id: Int,

    @NotNull
    @ColumnInfo("task_title")
    var task_title: String,

    @NotNull
    @ColumnInfo("task_state")
    var task_state: Int = 0,

    @NotNull
    @ColumnInfo("task_reminder")
    var task_reminder: String = "Today",

    @NotNull
    @ColumnInfo("task_date")
    var task_date: String,
)
