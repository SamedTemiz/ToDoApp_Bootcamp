package com.timrashard.todoapp_bootcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.timrashard.todoapp_bootcamp.presentation.screen.DashboardScreen
import com.timrashard.todoapp_bootcamp.presentation.screen.TaskListScreen
import com.timrashard.todoapp_bootcamp.ui.theme.TodoApp_BootcampTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoApp_BootcampTheme {
//                DashboardScreen()
                TaskListScreen()
            }
        }
    }
}