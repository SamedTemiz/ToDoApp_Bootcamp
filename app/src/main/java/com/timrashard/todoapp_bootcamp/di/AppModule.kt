package com.timrashard.todoapp_bootcamp.di

import android.content.Context
import androidx.room.Room
import com.timrashard.todoapp_bootcamp.data.datasource.TaskDataSource
import com.timrashard.todoapp_bootcamp.data.repository.TaskRepository
import com.timrashard.todoapp_bootcamp.data.room.Database
import com.timrashard.todoapp_bootcamp.data.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // ROOM DATABASE
    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao {
        val database = Room
            .databaseBuilder(context, Database::class.java, "todoapp.sqlite")
            .createFromAsset("todoapp.sqlite")
            .build()
        return database.getTaskDao()
    }

    @Provides
    @Singleton
    fun provideTaskDataSource(taskDao: TaskDao): TaskDataSource {
        return TaskDataSource(taskDao)
    }

    @Provides
    @Singleton
    fun provideRepository(taskDataSource: TaskDataSource): TaskRepository {
        return TaskRepository(taskDataSource)
    }
}