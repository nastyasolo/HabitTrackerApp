package com.example.habittrackerapp.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittrackerapp.HabitRepository
import com.example.habittrackerapp.android.screens.AddHabitScreen
import com.example.habittrackerapp.android.screens.HabitTrackerScreen
import com.example.habittrackerapp.android.screens.TaskScreen


@Composable
fun MainNavigation(repository: HabitRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "habits") {
        composable("habits") {
            HabitTrackerScreen(
                repository = repository,
                onAddHabit = { navController.navigate("addHabit") },
                onNavigateToTasks = { navController.navigate("tasks") }
            )
        }
        composable("tasks") {
            TaskScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("addHabit") {
            AddHabitScreen(
                onSave = { newHabit ->
                    repository.addHabit(newHabit)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}