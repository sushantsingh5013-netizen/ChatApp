package com.example.chatapp.presentation.chat.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    name : String,
    isOnline : Boolean,
    onBack : () -> Unit,
    onCall : () -> Unit,
    onMore : () -> Unit
){
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE2E5EF)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = name.firstOrNull()?.uppercase() ?: "?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Column(

                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(
                                color = if(isOnline) Color(0xFF28B879)
                                else Color.DarkGray
                            )
                    )
                    Text(
                        text = if (isOnline) "Online" else "Offline",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }

            }
        },
        navigationIcon = {
            IconButton (
                onClick = onBack
            ){
                Icon(
                    Icons.Filled.ChevronLeft,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onCall
            ) {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "call"
                )
            }
            IconButton(
                onClick = onMore
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}