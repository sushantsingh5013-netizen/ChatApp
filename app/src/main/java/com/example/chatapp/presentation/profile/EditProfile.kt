package com.example.chatapp.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Preview(showSystemUi = true)
@Composable
fun EditProfile(){
    var name by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            ProfileTopBar("Edit Profile")
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = "", // todo load profile image
                contentDescription = null
            )

            OutlinedTextField(
                value = name, // todo load name from user table in firebase
                onValueChange = { name = it },
                label = {
                    Text(
                        text = "Full Name"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal  = 16.dp),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "johndoe@gmail.com"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal  = 16.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = false
            )

            Button(
                onClick = {
                    // todo save to firebase Firestore
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Save Changes"
                )
            }

        }
    }
}