package com.timrashard.todoapp_bootcamp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.timrashard.todoapp_bootcamp.R
import com.timrashard.todoapp_bootcamp.data.entity.Task
import com.timrashard.todoapp_bootcamp.presentation.component.PlanCardComponent
import com.timrashard.todoapp_bootcamp.presentation.component.TaskItemComponent
import com.timrashard.todoapp_bootcamp.presentation.viewmodel.DashboardViewModel
import com.timrashard.todoapp_bootcamp.ui.theme.Blue
import com.timrashard.todoapp_bootcamp.ui.theme.LightBlue
import com.timrashard.todoapp_bootcamp.ui.theme.LightPink
import com.timrashard.todoapp_bootcamp.ui.theme.MintGreen
import com.timrashard.todoapp_bootcamp.ui.theme.SmokeBlue
import com.timrashard.todoapp_bootcamp.ui.theme.SoftBlue
import com.timrashard.todoapp_bootcamp.ui.theme.SoftYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel) {
    val taskList by viewModel.taskList.observeAsState(emptyList())
    val searchText = remember { mutableStateOf("") }

    LaunchedEffect(true) {
        viewModel.getAllTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "Hello Samed,", fontSize = 18.sp)
                        Text(text = "You have work today", fontSize = 16.sp, color = Color.Gray)
                    }
                },
                actions = {
                    Row(Modifier.padding(end = 16.dp)) {
                        IconButton(
                            onClick = {
                                // TODO notification
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notification),
                                contentDescription = "Notification",
                                tint = Color.Gray,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                // TODO more
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more),
                                contentDescription = "More",
                                tint = Color.Gray,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Blue,
                contentColor = Color.White,
                onClick = {
                    navController.navigate("task_screen")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add Contact",
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    PlanCardComponent(
                        title = "Today",
                        count = "-",
                        icon = R.drawable.ic_watch,
                        color = SoftBlue,
                        modifier = Modifier.weight(1f)
                    ) {
                        // TODO today tasks
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    PlanCardComponent(
                        title = "Scheduled",
                        count = "-",
                        icon = R.drawable.ic_scheduled,
                        color = SoftYellow,
                        modifier = Modifier.weight(1f)
                    ) {
                        // TODO scheduled tasks
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth()
                ) {
                    PlanCardComponent(
                        title = "All",
                        count = "-",
                        icon = R.drawable.ic_watch,
                        color = MintGreen,
                        modifier = Modifier.weight(1f)
                    ) {
                        // TODO All tasks
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    PlanCardComponent(
                        title = "Overdue",
                        count = "-",
                        icon = R.drawable.ic_scheduled,
                        color = LightPink,
                        modifier = Modifier.weight(1f)
                    ) {
                        // TODO Overdue tasks
                    }
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Today's Task",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .background(LightBlue, shape = RoundedCornerShape(16.dp))
                    ) {
                        BasicTextField(
                            value = searchText.value,
                            onValueChange = {
                                searchText.value = it
                                viewModel.searchTask(it)
                            },
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )

                        if (searchText.value.isEmpty()) {
                            Text(
                                text = "Search...",
                                color = Color.DarkGray,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(taskList) { task ->
                        val taskJson = Gson().toJson(task)

                        TaskItemComponent(
                            task = task,
                            onTaskClick = {
                                navController.navigate("task_details_screen/$taskJson")
                            },
                            onCompleteClick = {
                                viewModel.taskUpdate(task)
                            },
                            onDeleteClick = {
                                viewModel.deleteTask(task)
                            }
                        )
                    }
                }
            }
        }
    }
}