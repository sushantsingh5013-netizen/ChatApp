package com.example.chatapp.presentation.people.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.chatapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopBar(){
    TopAppBar(
        title = {
            Text(
                text = "People",
                fontSize = 32.sp,
                fontWeight = FontWeight.W900
            )
        },
        actions = {
            IconButton(
                onClick = {
                    //todo add dropdown menu
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ellipsis_vertical_solid_full),
                    contentDescription = "vertical ellipsis icon"
                )
            }
        }
    )
}