package com.timrashard.todoapp_bootcamp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.todoapp_bootcamp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TaskListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All Task List", fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                item {
                    TodoCard(
                        title = "Grocery",
                        notes = "15 Notes",
                        completed = "7 Completed",
                        backgroundColor = Color(0xFFF9DEFD),
                        modifier = Modifier.offset(y = 0.dp)
                    )
                }

                item {
                    TodoCard(
                        title = "Educational",
                        notes = "6 Notes",
                        completed = "2 Completed",
                        backgroundColor = Color(0xFFB4C4FF),
                        modifier = Modifier.offset(y = -40.dp)
                    )
                }

                item {
                    TodoCard(
                        title = "Home Related",
                        notes = "8 Notes",
                        completed = "5 Completed",
                        backgroundColor = Color(0xFFFFF57F),
                        modifier = Modifier.offset(y = -80.dp)
                    )
                }

                item {
                    TodoCard(
                        title = "Work Related",
                        notes = "14 Notes",
                        completed = "12 Completed",
                        backgroundColor = Color(0xFF339363),
                        modifier = Modifier.offset(y = -120.dp)
                    )
                }

                item {
                    TodoCard(
                        title = "Mandatory Work",
                        notes = "3 Notes",
                        completed = "2 Completed",
                        backgroundColor = Color(0xFFFFC0F5),
                        modifier = Modifier.offset(y = -160.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TodoCard(title: String, notes: String, completed: String, backgroundColor: Color, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(modifier = Modifier.padding(20.dp)) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.background(
                            Color.White.copy(alpha = 0.5f),
                            RoundedCornerShape(10.dp)
                        )
                    ) {
                        Text(
                            text = completed,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = notes, fontSize = 18.sp, color = Color.Black)

                    Icon(
                        painter = painterResource(id = R.drawable.ic_open),
                        contentDescription = "More",
                        tint = Color.DarkGray,
                    )
                }
            }
        }
    }
}

/*
* LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(todoList.size) { index ->
                    val todoItem = todoList[index]

                    // Kartların dinamik olarak yığılmış gibi görünmesini sağlamak için offset ayarlıyoruz
                    TodoCard(
                        title = todoItem.title,
                        notes = todoItem.notes,
                        completed = todoItem.completed,
                        backgroundColor = todoItem.backgroundColor,
                        modifier = Modifier.offset(y = (-index * 20).dp) // Kartları yığılmış gibi göstermek için offset kullanıyoruz
                    )
                }
            }
* */