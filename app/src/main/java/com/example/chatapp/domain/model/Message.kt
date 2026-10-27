package com.example.chatapp.domain.model

data class Message(
    val id : String = "",
    val chatId : String = "",
    val senderId : String = "",
    val text : String = "",
    val timestamp : Long = 0L,
    val type : MessageType = MessageType.TEXT
)

enum class MessageType{
    TEXT,
    IMAGE
}
