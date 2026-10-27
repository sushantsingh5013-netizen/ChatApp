package com.example.chatapp.data.model

import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.model.MessageType
import com.google.firebase.firestore.DocumentSnapshot


data class MessageDto(
    val id : String = "",
    val chatId : String = "",
    val senderId : String = "",
    val text : String = "",
    val timestamp: Long = 0L,
    val type : String = "TEXT"
){
    fun toDomain() : Message {
        return Message(
            id = id,
            chatId = chatId,
            senderId = senderId,
            text = text,
            timestamp = timestamp,
            type = runCatching {
                MessageType.valueOf(type)
            }.getOrDefault(MessageType.TEXT)
        )
    }

    companion object{
        fun fromDocument(
            document : DocumentSnapshot,
            chatId : String
        ) : MessageDto {
            return MessageDto(
                id = document.id,
                chatId = chatId,
                senderId = document.getString("senderId") ?: "",
                text = document.getString("text") ?: "",
                timestamp = document.getLong("timestamp") ?: 0L,
                type = document.getString("type") ?: "TEXT"
            )
        }
    }
}
