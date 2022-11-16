package com.example.songlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform