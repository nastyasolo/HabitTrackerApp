package com.example.habittrackerapp.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.habittrackerapp.Habit
import com.example.habittrackerapp.HabitRepository

@Composable
fun HabitTrackerScreen(
    repository: HabitRepository,
    onAddHabit: () -> Unit,
    onNavigateToTasks: () -> Unit
) {
    val habits by repository.habits.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Мои привычки",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Всего: ${habits.size} • Выполнено: ${habits.count { it.isCompleted }}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(habits, key = { it.id }) { habit ->
                HabitItem(
                    habit = habit,
                    onCheckedChange = {
                        repository.toggleHabit(habit.id)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onAddHabit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Добавить привычку")
            }

            OutlinedButton(
                onClick = onNavigateToTasks,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Перейти к задачам")
            }
        }
    }
}

@Composable
fun HabitItem(habit: Habit, onCheckedChange: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = habit.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                if (habit.description.isNotBlank()) {
                    Text(
                        text = habit.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Стрик: ${habit.streak}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = habit.type.displayName,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
            Checkbox(
                checked = habit.isCompleted,
                onCheckedChange = { onCheckedChange() }
            )
        }
    }
}