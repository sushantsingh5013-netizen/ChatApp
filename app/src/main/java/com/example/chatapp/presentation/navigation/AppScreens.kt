package com.example.chatapp.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
object Register
@Serializable
object Login
@Serializable
data class ChatRoute(
    val chatId : String,
    val otherUserId : String
)

@Serializable
object Profile
@Serializable
object Home

@Serializable
object People

@Serializable
object Call

@Serializable
object Add