package com.example.chatapp.data.model

import com.example.chatapp.domain.model.UserProfile
import com.google.firebase.firestore.DocumentSnapshot

data class UserProfileDto(
    val uid : String = "",
    val name : String = "",
    val email : String = "",
    val profileImage : String? = null,
    val isOnline : Boolean = false
){
    fun toDomain() : UserProfile{
        return UserProfile(
            uid = uid,
            name = name,
            email = email,
            profileImage = profileImage,
            isOnline = isOnline
        )
    }

    companion object{
        fun fromDocument(
            document : DocumentSnapshot
        ) : UserProfileDto{
            return UserProfileDto(
                uid = document.getString("uid") ?: "",
                name = document.getString("name") ?: "",
                email = document.getString("email") ?: "",
                profileImage = document.getString("profileImage"),
                isOnline = document.getBoolean("isOnline") ?: false

            )
        }
    }
}
