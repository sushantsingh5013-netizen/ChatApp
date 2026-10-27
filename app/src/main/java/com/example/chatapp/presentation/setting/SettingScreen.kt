package com.example.chatapp.presentation.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatapp.presentation.profile.ProfileTopBar

@Preview
@Composable
fun SettingScreen(

){
    Scaffold(
        topBar = {
            ProfileTopBar(
                "Settings"
            )
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Edit Profile"
                        )
                    }
                    Row{
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Account Settings"
                        )
                    }
                    Row {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Privacy"
                        )
                    }
                    Row {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }

                }
            }

            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Notifications"
                        )
                    }
                    Row {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }

                }
            }

            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row() {
                        Icon(
                            Icons.AutoMirrored.Default.Chat,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Chat Settings"
                        )
                    }
                    Row() {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row() {
                        Icon(
                            Icons.AutoMirrored.Default.HelpOutline,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Help & Support"
                        )
                    }
                    Row() {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

            OutlinedButton(
                onClick = {},//onEditButtonClick
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row() {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "About"
                        )
                    }
                    Row() {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {}
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Default.Logout,
                        contentDescription = null,
                        tint = Color.Red
                    )
                    Text(
                        text = "Logout",
                        color = Color.Red
                    )
                }
            }

        }
    }
}