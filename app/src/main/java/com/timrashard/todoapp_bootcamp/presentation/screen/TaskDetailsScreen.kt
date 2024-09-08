package com.timrashard.todoapp_bootcamp.presentation.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.timrashard.todoapp_bootcamp.R
import com.timrashard.todoapp_bootcamp.data.entity.Task
import com.timrashard.todoapp_bootcamp.presentation.component.DatePickerComponent
import com.timrashard.todoapp_bootcamp.presentation.component.TaskItemComponent
import com.timrashard.todoapp_bootcamp.presentation.component.TimePickerComponent
import com.timrashard.todoapp_bootcamp.presentation.viewmodel.TaskViewModel
import com.timrashard.todoapp_bootcamp.ui.theme.Blue
import com.timrashard.todoapp_bootcamp.ui.theme.SoftPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    task: Task,
    navController: NavController,
    viewModel: TaskViewModel
) {
    val taskTitle = remember { mutableStateOf("") }
    val taskDate = remember { mutableStateOf("") }
    val taskReminder = remember { mutableStateOf("") }
    val fieldError = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        taskTitle.value = task.task_title
        taskDate.value = task.task_date
        taskReminder.value = task.task_reminder
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Back", fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        ElevatedCard(
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(containerColor = SoftPink),
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Update current task",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )

                Column {
                    Text(
                        text = "Task Title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = taskTitle.value,
                        onValueChange = { newText ->
                            fieldError.value = false
                            taskTitle.value = newText
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp
                        ),
                        placeholder = {
                            Text(text = "Enter task title", fontSize = 18.sp)
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            errorContainerColor = Color.White,
                        ),
                        isError = fieldError.value,
                        maxLines = 2,
                        minLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                if (fieldError.value) Color.Red else Color.Gray,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .clip(RoundedCornerShape(25.dp))
                    )
                }

                Column {
                    Text(text = "Due Date", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.height(8.dp))

                    DatePickerComponent(
                        currentDate = task.task_date,
                        selectedDate = {
                            taskDate.value = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Column {
                    Text(text = "Set Reminder", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.height(8.dp))

                    TimePickerComponent(
                        currentTime = task.task_reminder,
                        selectedTime = {
                            taskReminder.value = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Column {
                    Text(text = "Preview", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.height(8.dp))

                    TaskItemComponent(
                        task = Task(
                            task_id = 0,
                            task_title = taskTitle.value,
                            task_state = 0,
                            task_date = taskDate.value,
                            task_reminder = taskReminder.value
                        ),
                        onTaskClick = {},
                        onCompleteClick = {}
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (taskTitle.value.isNotEmpty()) {
                                viewModel.updateTask(
                                    task.copy(
                                        task_title = taskTitle.value,
                                        task_date = taskDate.value,
                                        task_reminder = taskReminder.value,
                                    )
                                )

                                navController.popBackStack()
                            } else {
                                fieldError.value = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Update",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}