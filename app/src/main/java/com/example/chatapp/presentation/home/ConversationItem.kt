package com.example.chatapp.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import com.example.chatapp.domain.model.UserProfile
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConversationItem(
    user : UserProfile,
    lastMessage : String,
    lastMessageAt : Long,
    onClick : () -> Unit,
    modifier : Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(!user.profileImage.isNullOrBlank()){
            AsyncImage(
                model = user.profileImage,
                contentDescription = "profile image",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }else{
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = user.name.firstOrNull()?.uppercase()?: "?",
                    color = Color.DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = user.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = lastMessage.ifBlank { "Start a conversation" },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = formatConversationTime(lastMessageAt),
            maxLines = 1
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatConversationTime(
    timestamp : Long
    ) : String {
    if(timestamp <= 0L) return ""
        val messageTime = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())

    val today = LocalDate.now()
    val messageDate = messageTime.toLocalDate()

    return when(messageDate){
        today-> messageTime.format(
            DateTimeFormatter.ofPattern("h:mm:a")
        )
        today.minusDays(1) -> "Yesterday"
        else -> messageTime.format(
            DateTimeFormatter.ofPattern("dd/MM/yy")
        )
    }
}