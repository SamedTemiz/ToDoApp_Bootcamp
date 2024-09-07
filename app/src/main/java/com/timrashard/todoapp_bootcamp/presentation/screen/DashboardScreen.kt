package com.timrashard.todoapp_bootcamp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.todoapp_bootcamp.R
import com.timrashard.todoapp_bootcamp.presentation.component.PlanCardComponent
import com.timrashard.todoapp_bootcamp.presentation.component.TaskItemComponent
import com.timrashard.todoapp_bootcamp.ui.theme.Blue
import com.timrashard.todoapp_bootcamp.ui.theme.LightPink
import com.timrashard.todoapp_bootcamp.ui.theme.MintGreen
import com.timrashard.todoapp_bootcamp.ui.theme.SoftBlue
import com.timrashard.todoapp_bootcamp.ui.theme.SoftYellow

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DashboardScreen() {
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
                    // TODO add task screen
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
        Column(modifier = Modifier.padding(paddingValues)) {
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
                        count = "6",
                        icon = R.drawable.ic_watch,
                        color = SoftBlue,
                        modifier = Modifier.weight(1f)
                    ) {
                        // TODO today tasks
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    PlanCardComponent(
                        title = "Scheduled",
                        count = "5",
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
                        count = "24",
                        icon = R.drawable.ic_watch,
                        color = MintGreen,
                        modifier = Modifier.weight(1f)
                    ) {
                        // TODO All tasks
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    PlanCardComponent(
                        title = "Overdue",
                        count = "3",
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
                Text(text = "Today's Task", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(7) {
                        TaskItemComponent()
                    }
                }
            }
        }
    }
}