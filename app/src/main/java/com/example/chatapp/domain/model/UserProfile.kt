package com.example.chatapp.domain.model

data class UserProfile(
    val uid : String,
    val name : String,
    val email : String,
    val profileImage : String? = null,
    val isOnline : Boolean = false
)
