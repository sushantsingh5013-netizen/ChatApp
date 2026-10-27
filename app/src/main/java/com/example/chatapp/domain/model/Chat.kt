package com.example.chatapp.domain.model

data class Chat(
    val id : String = "",
    val participantIds : List<String> = emptyList(),
    val createdAt : Long = 0L,
    val lastMessage : String = "",
    val lastMessageAt : Long = 0L
)
