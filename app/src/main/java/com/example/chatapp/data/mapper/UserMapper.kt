package com.example.chatapp.data.mapper

import com.example.chatapp.domain.model.UserProfile
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUserProfile() : UserProfile{
    return UserProfile(
        uid = uid,
        name = displayName.orEmpty(),
        email = email.orEmpty(),
        profileImage = photoUrl?.toString(),
        isOnline = false
    )
}