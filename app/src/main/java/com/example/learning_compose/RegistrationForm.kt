package com.example.learning_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm(

    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    var hasDate by remember {
        mutableStateOf(false)
    }
    var name by remember { mutableStateOf("") }
    var profession by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker
    )
    var open by remember {
        mutableStateOf(false)
    }
    val dateFormatted by remember {
        derivedStateOf {
            SimpleDateFormat.getDateInstance()
                .format(Date(datePickerState.selectedDateMillis ?: Date().time))
                .toString()
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(label = { Text("name") }, value = name, onValueChange = { text ->
            name = text
        })

        OutlinedTextField(
            label = { Text("profession") },
            value = profession,
            onValueChange = { text ->
                profession = text
            })

        Button(onClick = { open = true }) {
            Text(if (!hasDate) "Select dob" else dateFormatted)
        }

        Button(onClick = {
            val result: Boolean = Database.createUser(
                name,
                profession,
                datePickerState.selectedDateMillis
            )
            scope.launch {
                snackbarHostState.showSnackbar(if (result) "User Created." else "An error has occured")
            }
        }) {
            Text("Create User")
        }



        if (open) {
            DatePickerDialog(
                onDismissRequest = { open = false },
                confirmButton = {
                    Button(onClick = { hasDate = true; open = false }) {
                        Text(text = "Ok")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
