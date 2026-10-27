package com.example.chatapp.presentation.chat.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapp.presentation.chat.ChatMessageUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MessageBubble(
    message : ChatMessageUi
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMine){
            Arrangement.End
        }else{
            Arrangement.Start
        }
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 200.dp)
                .clip(RoundedCornerShape(
                    topStart = 18.dp,
                    topEnd = 18.dp,
                    bottomStart = if(message.isMine) 18.dp else 5.dp,
                    bottomEnd = if(message.isMine) 5.dp else 18.dp
                ))
                .background(
                    if(message.isMine){
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF5146F5),
                                Color(0xFF6657FF)
                            )
                        )
                    }else{
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFF0F2f7),
                                Color(0xFFF0F2f7)
                            )
                        )
                    }
                )
                .padding(
                    horizontal = 13.dp,
                    vertical = 10.dp
                )
        ) {
            Text(
                text = message.text,
                color = if(message.isMine){
                    Color.White
                }else{
                    Color(0xFF242735)
                },
                fontSize = 14.sp
            )
            Spacer(Modifier.height(5.dp))

            Text(
                text = formatMessageTime(message.time),
                color = if( message.isMine){
                    Color.White.copy(alpha = 0.75f)
                }else{
                    Color.DarkGray
                },
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

private fun formatMessageTime(time : Long) : String{
    if(time <= 0L) return ""
    return SimpleDateFormat(
        "hh:mm a",
        Locale.getDefault()
    ).format(Date(time))
}