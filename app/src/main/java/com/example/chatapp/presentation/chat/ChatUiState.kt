package com.example.chatapp.presentation.chat

import com.example.chatapp.domain.model.Chat
import com.example.chatapp.domain.model.UserProfile

data class ChatUiState(
    val isLoading : Boolean = false,
    val chat : Chat? = null,
    val otherUser : UserProfile? = null,
    val isProfileLoading : Boolean = false,
    val error: String? = null,
    val profileError : String? = null
)
