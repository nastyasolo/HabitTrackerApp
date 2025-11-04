package com.example.habittrackerapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HabitRepository {
    private val _habits = MutableStateFlow<List<Habit>>(
        listOf(
            Habit(id = "1", name = "Бег", description = "Утренняя пробежка"),
            Habit(id = "2", name = "Чтение", description = "30 минут в день"),
            Habit(id = "3", name = "Вода", description = "2 литра воды")
        )
    )

    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    fun addHabit(habit: Habit) {
        _habits.value = _habits.value + habit
    }

    fun toggleHabit(id: String) {
        _habits.value = _habits.value.map { habit ->
            if (habit.id == id) {
                val newIsCompleted = !habit.isCompleted
                val newStreak = if (newIsCompleted) habit.streak + 1 else habit.streak
                habit.copy(
                    isCompleted = newIsCompleted,
                    streak = newStreak
                )
            } else habit
        }
    }

    fun deleteHabit(id: String) {
        _habits.value = _habits.value.filter { it.id != id }
    }
}