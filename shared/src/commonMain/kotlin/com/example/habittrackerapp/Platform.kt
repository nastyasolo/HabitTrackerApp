package com.example.habittrackerapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform