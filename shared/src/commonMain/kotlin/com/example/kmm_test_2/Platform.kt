package com.example.kmm_test_2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform