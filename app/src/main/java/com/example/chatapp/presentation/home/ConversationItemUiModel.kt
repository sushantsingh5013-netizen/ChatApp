package com.example.chatapp.presentation.home

import com.example.chatapp.domain.model.Chat
import com.example.chatapp.domain.model.UserProfile

data class ConversationItemUiModel(
    val chat : Chat,
    val otherUser : UserProfile
)
