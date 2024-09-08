package com.timrashard.todoapp_bootcamp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.todoapp_bootcamp.R
import com.timrashard.todoapp_bootcamp.data.entity.Task
import com.timrashard.todoapp_bootcamp.ui.theme.Blue
import com.timrashard.todoapp_bootcamp.ui.theme.LightBlue
import com.timrashard.todoapp_bootcamp.ui.theme.SoftRed

@Composable
fun TaskItemComponent(
    task: Task,
    onTaskClick: () -> Unit,
    onCompleteClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    val isDone = remember { mutableStateOf(if (task.task_state == 1) true else false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color = LightBlue, shape = RoundedCornerShape(30.dp))
            .alpha(if (isDone.value) 0.5f else 1f)
            .clickable {
                onTaskClick()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        isDone.value = !isDone.value
                        task.task_state = if (isDone.value) 1 else 0
                        onCompleteClick()
                    }
            ) {
                Icon(
                    painter = painterResource(id = if (!isDone.value) R.drawable.ic_undone else R.drawable.ic_done),
                    contentDescription = "Today",
                    tint = Blue,
                    modifier = Modifier.size(28.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                if(task.task_date.isNotEmpty() || task.task_reminder.isNotEmpty()){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if(task.task_date.isNotEmpty()){
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(task.task_date, fontSize = 12.sp, color = Color.Gray)
                            }
                        }

                        Spacer(Modifier.width(8.dp))

                        if(task.task_reminder.isNotEmpty()){
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_scheduled),
                                    contentDescription = "",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(task.task_reminder, fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                }

                Text(
                    text = task.task_title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = if (isDone.value) TextDecoration.LineThrough else TextDecoration.None,
                    overflow = TextOverflow.Ellipsis
                )
            }
            // Task item crud actions button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                IconButton(
                    onClick = {
                        onDeleteClick()
                    }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Today",
                        tint = SoftRed,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}