package com.example.habittrackerapp.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.example.habittrackerapp.Habit
import com.example.habittrackerapp.HabitType
import java.util.UUID

@Composable
fun AddHabitScreen(
    onSave: (Habit) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(HabitType.DAILY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Добавить привычку",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Название привычки *") },
            modifier = Modifier.fillMaxWidth(),
            isError = name.isBlank()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Тип привычки:", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HabitType.values().forEach { type ->
                FilterChip(
                    selected = selectedType == type,
                    onClick = { selectedType = type },
                    label = { Text(type.displayName) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onCancel) {
                Text("Отмена")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    val newHabit = Habit(
                        id = UUID.randomUUID().toString(),
                        name = name.trim(),
                        description = description.trim(),
                        type = selectedType
                    )
                    onSave(newHabit)
                },
                enabled = name.isNotBlank()
            ) {
                Text("Сохранить")
            }
        }
    }
}

// Добавим расширение для отображения имени типа
private val HabitType.displayName: String
    get() = when (this) {
        HabitType.DAILY -> "Ежедневно"
        HabitType.WEEKLY -> "Еженедельно"
    }