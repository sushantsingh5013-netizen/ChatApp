package com.example.chatapp.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.usecase.GetCurrentUser
import com.example.chatapp.domain.usecase.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getUser : GetUsers,
    private val getCurrentUser : GetCurrentUser
): ViewModel() {
    private val _uiState = MutableStateFlow(PeopleUiState())

    val uiState: StateFlow<PeopleUiState> = _uiState.asStateFlow()

    val currentUserid : String?
        get() = getCurrentUser()?.uid

    init {
        observeUsers()
    }

    private fun observeUsers(){
        viewModelScope.launch {
            try {
                val currentUserUid = getCurrentUser()?.uid

                getUser().collect {
                    users->
                    val otherUsers = users.filter {
                        user->
                        user.uid != currentUserUid
                    }

                    _uiState.update {
                        it.copy(
                            users = otherUsers,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } catch (e : CancellationException){
                throw e
            } catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message?: "Failed to load users"
                    )
                }
            }
        }
    }

    fun onSearchQueryChange(
        query : String
    ){
        _uiState.update {
            it.copy(
                searchQuery = query
            )
        }
    }


}