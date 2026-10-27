package com.example.chatapp.presentation.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    text : String
){
    CenterAlignedTopAppBar(
        title = {
            Text(
            text = text,
            fontSize = 32.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    // todo navigate to last screen
                }
            ){
                Icon(
                    Icons.Default.ChevronLeft,
                    contentDescription = "back icon"
                )
            }
        }
    )
}