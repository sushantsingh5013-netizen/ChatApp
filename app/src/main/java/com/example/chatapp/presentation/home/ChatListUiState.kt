package com.example.chatapp.presentation.home

import com.example.chatapp.domain.model.Chat

data class ChatListUiState(
    val isLoading : Boolean = true,
    val conversations : List<ConversationItemUiModel> = emptyList(),
    val error : String? = null
)
