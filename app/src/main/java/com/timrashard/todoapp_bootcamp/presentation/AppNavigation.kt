package com.timrashard.todoapp_bootcamp.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.timrashard.todoapp_bootcamp.data.entity.Task
import com.timrashard.todoapp_bootcamp.presentation.screen.DashboardScreen
import com.timrashard.todoapp_bootcamp.presentation.screen.CreateTaskScreen
import com.timrashard.todoapp_bootcamp.presentation.screen.TaskDetailsScreen
import com.timrashard.todoapp_bootcamp.presentation.viewmodel.DashboardViewModel
import com.timrashard.todoapp_bootcamp.presentation.viewmodel.TaskViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val taskViewModel = hiltViewModel<TaskViewModel>()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                navController = navController,
                viewModel = hiltViewModel<DashboardViewModel>()
            )
        }

        composable("task_screen") {
            CreateTaskScreen(
                navController = navController,
                viewModel = taskViewModel
            )
        }

        composable("task_details_screen/{task}",
            arguments = listOf(
                navArgument("task") { type = NavType.StringType }
            )
        ) {
            val json = it.arguments?.getString("task")
            val taskObject = Gson().fromJson(json, Task::class.java)

            TaskDetailsScreen(
                task = taskObject,
                navController = navController,
                viewModel = taskViewModel
            )
        }
    }
}