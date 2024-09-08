package com.timrashard.todoapp_bootcamp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerComponent(
    currentDate: String?,
    selectedDate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDatetime by remember { mutableStateOf<LocalDate?>(null) }

    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(25.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(25.dp))
            .clickable { showDatePicker = true }
            .padding(16.dp)
    ) {
        Text(
            text = selectedDatetime?.format(DateTimeFormatter.ofPattern("dd MMMM")) ?: currentDate ?: "Select a date",
            fontSize = 18.sp,
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDateSelected = { date ->
                selectedDatetime = date
                showDatePicker = false
                selectedDate(date.format(DateTimeFormatter.ofPattern("dd MMMM")))
            },
            onDismissRequest = { showDatePicker = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
            color = Color.White,
            contentColor = Color.Black
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel", color = Color.Red)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            datePickerState.selectedDateMillis?.let {
                                onDateSelected(LocalDate.ofEpochDay(it / (24 * 60 * 60 * 1000)))
                            }
                        }
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}