package com.timrashard.todoapp_bootcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.timrashard.todoapp_bootcamp.presentation.AppNavigation
import com.timrashard.todoapp_bootcamp.presentation.viewmodel.DashboardViewModel
import com.timrashard.todoapp_bootcamp.presentation.viewmodel.TaskViewModel
import com.timrashard.todoapp_bootcamp.ui.theme.TodoApp_BootcampTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoApp_BootcampTheme {
                TodoApp()
            }
        }
    }
}

@Composable
fun TodoApp() {
    AppNavigation()
}