package com.example.chatapp.data.model

import com.example.chatapp.domain.model.Chat
import com.google.firebase.firestore.DocumentSnapshot

data class ChatDto(
    val id : String = "",
    val participantIds : List<String> = emptyList(),
    val createdAt : Long = 0L,
    val lastMessage : String = "",
    val lastMessageAt : Long = 0L
){
    fun toDomain() : Chat{
        return Chat(
            id = id,
            participantIds = participantIds,
            createdAt = createdAt,
            lastMessage = lastMessage,
            lastMessageAt = lastMessageAt
        )
    }

    companion object{
        fun fromDocument(
            document : DocumentSnapshot
        ) : ChatDto{
            return ChatDto(
                id = document.id,
                participantIds = document.get("participantIds") as? List<String> ?: emptyList(),
                createdAt = document.getLong("createdAt")?: 0L,
                lastMessage = document.getString("lastMessage") ?: "",
                lastMessageAt = document.getLong("lastMessageAt")?: 0L
            )
        }
    }
}
