package com.example.chatapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapp.R

@Preview(showSystemUi = true, showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppTopBar(){
    TopAppBar(
        title = {
            Text(
                text = "ChatApp",
                fontSize = 32.sp,
                fontWeight = FontWeight.W900
            )
        },
        actions = {
            IconButton(
                onClick = {
                    //todo search chats
                },
                modifier = Modifier.background(Color.Transparent),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ){
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(
                onClick = {
                    //todo drop-down menu
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ellipsis_vertical_solid_full),
                    contentDescription = "ellipsis vertical icon"
                )
            }
        },
        windowInsets = WindowInsets.systemBars
    )
}