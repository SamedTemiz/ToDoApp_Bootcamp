package com.timrashard.todoapp_bootcamp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.todoapp_bootcamp.R
import com.timrashard.todoapp_bootcamp.ui.theme.Blue
import com.timrashard.todoapp_bootcamp.ui.theme.LightBlue

@Preview
@Composable
fun TaskItemComponent(
    isDone: Boolean = false,
    onClick: () -> Unit = {}
) {
    val isDone = remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightBlue, shape = RoundedCornerShape(30.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        isDone.value = !isDone.value
                        // TODO task done
                    }
            ) {
                Icon(
                    painter = painterResource(id = if(!isDone.value) R.drawable.ic_undone else R.drawable.ic_done),
                    contentDescription = "Today",
                    tint = Blue,
                    modifier = Modifier.size(28.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Today", fontSize = 12.sp, color = Color.Gray)
                    }

                    Spacer(Modifier.width(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_scheduled),
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("4:50 PM", fontSize = 12.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Project retrospective", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            // Task item crud actions button
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .size(28.dp)
//                    .clip(CircleShape)
//                    .background(Color.White)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_more),
//                    contentDescription = "Today",
//                    tint = Color.Gray,
//                    modifier = Modifier.size(16.dp)
//                )
//            }
        }
    }
}