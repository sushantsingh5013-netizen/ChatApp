package com.example.chatapp.presentation.people

import com.example.chatapp.domain.model.UserProfile

data class PeopleUiState(
    val users : List<UserProfile> = emptyList(),
    val isLoading : Boolean = true,
    val error : String? = null,
    val searchQuery : String = ""
)
