package com.example.chatapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapp.presentation.components.SignUpComponent

@Composable
fun LoginScreen(
    onRegisterButtonClick : () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isVisible by remember { mutableStateOf(false) }

        Text(
            text = "Welcome back",
            fontSize = 32.sp,
            fontWeight = FontWeight.W800,
            color = Color.DarkGray
        )
        Text(
            text = "Sign in to continue",
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.DarkGray
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            placeholder = {
                Text(
                    text = "Email",
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Person4,
                    contentDescription = "lock icon",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp)
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            )

        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Person icon",
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                Icon(
                    if (isVisible)
                    Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "lock icon",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {},

                ) {
                Text(
                    text = "Forgot password?"
                )
            }
        }


        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Login"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(Modifier.fillMaxWidth(0.5f).padding(4.dp))
            Text("OR")
            HorizontalDivider(Modifier.fillMaxWidth().padding(4.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        SignUpComponent()

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?"
            )
            TextButton(
                onClick = onRegisterButtonClick
            ) {
                Text(text = "Register")
            }
        }

    }
}
