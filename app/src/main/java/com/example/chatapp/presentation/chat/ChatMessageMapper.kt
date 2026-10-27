package com.example.chatapp.presentation.chat

import com.example.chatapp.domain.model.Message

fun Message.toChatMessageUi(
    currentUserId: String?
) : ChatMessageUi {
    return ChatMessageUi(
        id = id,
        text = text,
        time = timestamp,
        isMine = senderId == currentUserId
    )
}