package com.example.chatapp.presentation.people.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.chatapp.domain.model.UserProfile

@Composable
fun UserListComponent(
    user : UserProfile,
    onClick : () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row{
            AsyncImage(
                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(50)),
                model = user.profileImage,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = user.name,
                    fontSize = 14.sp
                )
                Text(
                    text = "Online",
                    fontSize = 10.sp
                )
            }
        }

        Button(
            onClick = onClick
        ) {
            Text("Chat")
        }
    }

}