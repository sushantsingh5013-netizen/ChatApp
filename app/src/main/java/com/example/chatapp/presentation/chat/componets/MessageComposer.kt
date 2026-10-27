package com.example.chatapp.presentation.chat.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageComposer(
    value : String,
    onValueChange : (String) -> Unit,
    onAttach : () -> Unit,
    onSend : () -> Unit
){
    Surface(
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .padding( horizontal = 12.dp, vertical = 10.dp ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = onAttach,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFF0F2F7), CircleShape)
            ) {
                Icon(
                    Icons.Default.AttachFile,
                    contentDescription = "attach file"
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F3F8))
                    .padding( horizontal = 14.dp, vertical = 12.dp ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ){
                    if (value.isEmpty()){
                        Text(
                            text = "Type a message...",
                            color = Color.DarkGray,
                            fontSize = 13.sp
                        )
                    }

                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color(0xFF242735),
                            fontSize = 13.sp
                        )
                    )

                    Icon(
                        Icons.Default.EmojiEmotions,
                        contentDescription = "emoji",
                        tint = Color.DarkGray
                    )

                }

                IconButton(
                    onClick = onSend,
                    enabled = value.isNotBlank(),
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            brush = Brush.linearGradient(colors = listOf(Color(0xFF6657FF), Color(0xFF5146F5))),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        Icons.AutoMirrored.Default.Send,
                        contentDescription = "Send message",
                        tint = Color.White
                    )
                }
            }
        }
    }
}