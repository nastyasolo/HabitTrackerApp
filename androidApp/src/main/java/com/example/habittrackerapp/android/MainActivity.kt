package com.example.habittrackerapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.habittrackerapp.HabitRepository


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitTrackerAppTheme {
                val repository = remember { HabitRepository() }
                MainNavigation(repository = repository)
            }
        }
    }
}

@Composable
fun HabitTrackerAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}

