package com.example.habittrackerapp

import kotlinx.datetime.Clock

data class Habit(
    val id: String,
    val name: String,
    val description: String = "",
    val type: HabitType = HabitType.DAILY,
    val streak: Int = 0,
    val isCompleted: Boolean = false,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)
enum class HabitType {
    DAILY {
        override val displayName: String
            get() = "Ежедневная"
    },
    WEEKLY {
        override val displayName: String
            get() = "Еженедельная"
    };

    abstract val displayName: String
}