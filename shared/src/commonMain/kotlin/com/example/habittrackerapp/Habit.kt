package com.example.habittrackerapp

import kotlinx.datetime.Clock

// data/Habit.kt
data class Habit(
    val id: String,
    val name: String,
    val description: String = "",
    val type: HabitType = HabitType.DAILY,
    val frequency: Int = 1, // раз в день/неделю
    val streak: Int = 0,
    val isCompleted: Boolean = false,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    val reminderTime: String? = null, // "HH:mm"
    val priority: Priority = Priority.MEDIUM
)

enum class HabitType {
    DAILY, WEEKLY
}

enum class Priority {
    LOW, MEDIUM, HIGH
}

// data/Task.kt
data class Task(
    val id: String,
    val title: String,
    val description: String = "",
    val deadline: Long? = null,
    val priority: Priority = Priority.MEDIUM,
    val isCompleted: Boolean = false,
    val category: String = ""
)