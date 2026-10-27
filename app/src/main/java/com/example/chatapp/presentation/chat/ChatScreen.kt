package com.example.chatapp.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.domain.model.Message
import com.example.chatapp.presentation.chat.componets.ChatTopBar
import com.example.chatapp.presentation.chat.componets.MessageBubble
import com.example.chatapp.presentation.chat.componets.MessageComposer

@Composable
fun ChatScreen(
    otherUserName : String,
    otherUserImage : String? = null,
    isOnline : Boolean = false,
    onBack : () -> Unit = {},
    onCall : () -> Unit = {},
    onMore : () -> Unit = {},
    onSendMessage : (String) -> Unit,
    messages : List<ChatMessageUi> = emptyList()
){

    var messageText by remember{ mutableStateOf("") }

    Scaffold(
        topBar = {
            ChatTopBar(
                name = otherUserName,
                isOnline = isOnline,
                onBack = onBack,
                onCall = onCall,
                onMore = onMore
            )
        },
        bottomBar = {
            MessageComposer(
                value = messageText,
                onValueChange = {messageText = it},
                onAttach = {},
                onSend = {
                    val text = messageText.trim()
                    if(text.isNotEmpty()){
                        onSendMessage(text)
                        messageText = ""
                    }
                }
            )
        }
    ) {
        innerPadding->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            reverseLayout = false,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            items(
                items = messages,
                key = { it.id }
            ){
                message->
                MessageBubble(
                    message = message
                )
            }
        }
    }

}
