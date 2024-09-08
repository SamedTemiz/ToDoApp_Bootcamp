package com.timrashard.todoapp_bootcamp.presentation.component

import android.annotation.SuppressLint
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.timrashard.todoapp_bootcamp.ui.theme.Blue
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePickerComponent(
    currentTime: String?,
    selectedTime: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTimeValue by remember { mutableStateOf<LocalTime?>(null) }

    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(25.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(25.dp))
            .clickable { showTimePicker = true }
            .padding(16.dp)
    ) {
        Text(
            text = selectedTimeValue?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: currentTime ?: "Select a time",
            fontSize = 18.sp,
        )
    }

    if (showTimePicker) {
        TimePickerDialog(
            onTimeSelected = { time ->
                selectedTimeValue = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                selectedTime(time)
                showTimePicker = false
            },
            onDismissRequest = { showTimePicker = false }
        )
    }
}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onTimeSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val currentTime = LocalTime.now()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = true,
    )

    Dialog(onDismissRequest = onDismissRequest, properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TimeInput(
                    state = timePickerState,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel", color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        val time = String.format("%02d:%02d", timePickerState.hour, timePickerState.minute)
                        onTimeSelected(time)
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue
                        )
                        ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}

