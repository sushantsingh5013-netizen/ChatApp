package com.example.chatapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.model.UserProfile
import com.example.chatapp.domain.usecase.GetCurrentUser
import com.example.chatapp.domain.usecase.LoginWithEmail
import com.example.chatapp.domain.usecase.RegisterWithEmail
import com.example.chatapp.domain.usecase.SaveUserProfile
import com.example.chatapp.domain.usecase.SignInWithGoogle
import com.example.chatapp.domain.usecase.SignOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogle: SignInWithGoogle,
    private val loginWithEmailUseCase : LoginWithEmail,
    private val registerWithEmailUseCase : RegisterWithEmail,
    private val saveUserProfile: SaveUserProfile,
    private val getCurrentUser: GetCurrentUser,
    private val signOut: SignOut
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    init {
        checkAuthSession()
    }

    private suspend fun persistUserProfile(user : User){
        saveUserProfile(
            UserProfile(
                uid = user.uid,
                name = user.name,
                email = user.email,
                profileImage = user.profileImage,
                isOnline = false
            )
        )
    }

    fun loginWithGoogle(idToken : String){
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = signInWithGoogle(idToken)

            result.fold(
                onSuccess = {
                    user->
                    try {
                        persistUserProfile(user)
                        _authState.value = AuthState.LoggedIn(user)
                    }catch(e : Exception){
                        _authState.value = AuthState.Error(
                            e.message?: "Failed to save user profile"
                        )
                    }

                },
                onFailure = {
                    error->
                    _authState.value = AuthState.Error(
                        error.message?: "Google login failed"
                    )
                }
            )
        }
    }

    fun resetState(){
        _authState.value = AuthState.Idle
    }

    fun loginWithEmail(
        email : String,
        password : String
    ){
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = loginWithEmailUseCase(
                email = email,
                password = password
            )

            result.fold(
                onSuccess = {
                    user->
                    try {
                        persistUserProfile(user)
                        _authState.value = AuthState.LoggedIn(user)
                    }catch (e : Exception){
                        _authState.value = AuthState.Error(
                            e.message?: "Failed to save user profile"
                        )
                    }
                },
                onFailure = {
                    error->
                    AuthState.Error(
                        error.message?: "Login failed. Please try again"
                    )
                }
            )

        }
    }

    fun registerWithEmail(
        name : String,
        email : String,
        password : String
    ){
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = registerWithEmailUseCase(
                name,
                email,
                password
            )

            result.fold(
                onSuccess = {
                    user->
                    try {
                        persistUserProfile(user)
                        _authState.value = AuthState.LoggedIn(user)
                    }catch (e : Exception){
                        _authState.value = AuthState.Error(
                            e.message?: "Failed to save user profile"
                        )
                    }
                },
                onFailure = { error->
                    _authState.value = AuthState.Error(
                        error.message?: "Registration failed. Please try again"
                    )
                }
            )
        }
    }

    private fun checkAuthSession(){
        viewModelScope.launch {
            try {
                val user = getCurrentUser()
                _authState.value = if(user!= null){
                    AuthState.LoggedIn(user)
                }else{
                    AuthState.LoggedOut
                }
            }catch (_ : Exception){
                _authState.value = AuthState.LoggedOut
            }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            signOut()
            resetState()
            _authState.value = AuthState.LoggedOut
        }
    }

}