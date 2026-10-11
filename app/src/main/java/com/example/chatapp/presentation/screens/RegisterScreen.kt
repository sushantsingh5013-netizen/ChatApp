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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chatapp.data.auth.GoogleAuthClient
import com.example.chatapp.presentation.components.SignUpComponent
import com.example.chatapp.presentation.navigation.auth.AuthState
import com.example.chatapp.presentation.navigation.auth.AuthViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onLoginButtonClick : () -> Unit,
    onRegisterSuccess : () -> Unit,
    viewModel : AuthViewModel,
    googleAuthClient : GoogleAuthClient,
    onLoggedOut : () -> Unit
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val authState by viewModel.authState
        .collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var name by remember {mutableStateOf("")}
        var email by remember {mutableStateOf("")}
        var password by remember {mutableStateOf("")}
        var confirmPassword by remember {mutableStateOf("")}
        var isPasswordVisible by remember { mutableStateOf(false) }
        var isConfirmPasswordVisible by remember { mutableStateOf(false) }

        Text(
            text = "Create Account",
            fontSize = 32.sp,
            color = Color.Gray,
            fontWeight = FontWeight.W700
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Let's get you started",
            fontSize = 18.sp,
            color = Color.Gray,
            fontWeight = FontWeight.W400
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            placeholder = {
                Text(
                    text = "Full Name",
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Person4,
                    contentDescription = "person icon",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            placeholder = {
                Text(
                    text = "Email",
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "person icon",
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
                .padding(vertical = 12.dp),
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
                    if (isPasswordVisible)
                        Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "lock icon",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            placeholder = {
                Text(
                    text = "Confirm Password",
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
                    if (isConfirmPasswordVisible)
                        Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "lock icon",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(16.dp)
        )

        Button(
            onClick = {
                viewModel.registerWithEmail(
                    name,
                    email,
                    password
                )
            },
            modifier = Modifier.padding(vertical = 12.dp).height(48.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                if(authState is AuthState.Loading){
                    CircularProgressIndicator()
                }else{
                    Text(
                        text = "Register",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.5f))
            Text("OR", color = Color.Gray)
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
        }

        Spacer(modifier = Modifier.height(12.dp))
        SignUpComponent(
            onGoogleSign = {
                scope.launch {
                    try {
                        val idToken = googleAuthClient.getGoogleIdToken(context)
                        viewModel.loginWithGoogle(idToken)
                    }catch (e : CancellationException){
                        throw e
                    }catch (e : Exception){
                        // show credential error TODO
                        e.message?: "google sign in failed try again"
                    }
                }
            },
            onAppleSignIn = {
                //todo
            },
            isLoading = authState is AuthState.Loading
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?"
            )
            TextButton(
                onClick = onLoginButtonClick
            ) {
                Text(text = "Login")
            }
        }

        when(val state = authState){
            is AuthState.Idle -> Unit
            is AuthState.Loading -> Unit
            is AuthState.LoggedIn -> {
                Text("WelCome, ${state.user.name}!")
                Text(state.user.email)
                onRegisterSuccess()
                // todo navigate to login screen
            }
            is AuthState.LoggedOut -> {
                // todo navigate to login screen
                onLoggedOut()
            }
            is AuthState.Error ->{
                // todo display error
            }
        }
    }

}