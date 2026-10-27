package com.example.chatapp.presentation.chat

data class ChatMessageUi(
    val id : String,
    val text : String,
    val time : Long,
    val isMine : Boolean
)