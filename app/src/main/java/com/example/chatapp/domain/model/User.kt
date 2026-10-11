package com.example.chatapp.domain.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val profileImage: String? = "",
    val isOnline: Boolean = false,
    val lastSeen: Long = 0L
)