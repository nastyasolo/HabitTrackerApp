package com.example.habittrackerapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HabitRepository {
    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    init {
        // Добавим тестовые данные
        _habits.value = listOf(
            Habit(
                id = "1",
                name = "Утренняя зарядка",
                description = "15 минут упражнений",
                type = HabitType.DAILY,
                streak = 5,
                isCompleted = false
            ),
            Habit(
                id = "2",
                name = "Чтение",
                description = "30 минут в день",
                type = HabitType.DAILY,
                streak = 3,
                isCompleted = true
            ),
            Habit(
                id = "3",
                name = "Прогулка",
                description = "Вечерняя прогулка 20 минут",
                type = HabitType.DAILY,
                streak = 7,
                isCompleted = false
            )
        )
    }

    fun addHabit(habit: Habit) {
        _habits.value = _habits.value + habit
    }

    fun toggleHabit(id: String) {
        _habits.value = _habits.value.map { habit ->
            if (habit.id == id) {
                val newIsCompleted = !habit.isCompleted
                val newStreak = if (newIsCompleted && !habit.isCompleted) {
                    habit.streak + 1
                } else if (!newIsCompleted) {
                    maxOf(0, habit.streak - 1)
                } else {
                    habit.streak
                }
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

    fun updateHabit(updatedHabit: Habit) {
        _habits.value = _habits.value.map { habit ->
            if (habit.id == updatedHabit.id) updatedHabit else habit
        }
    }
}