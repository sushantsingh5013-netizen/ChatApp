package com.example.chatapp.data.repo

import com.example.chatapp.data.model.UserProfileDto
import com.example.chatapp.domain.model.UserProfile
import com.example.chatapp.domain.repo.UserProfileRepo
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.collections.mapOf

class UserProfileRepoImpl @Inject constructor(
    private val firestore : FirebaseFirestore
) : UserProfileRepo {
    private val userCollection
        get() = firestore.collection("users")


    override suspend fun saveUserProfile(
        user: UserProfile
    ) {
        val document = userCollection.document(user.uid)

        firestore.runTransaction {
            transaction ->
            val snapshot = transaction.get(document)

            if(!snapshot.exists()){
                val dto = UserProfileDto(
                    uid = user.uid,
                    name = user.name,
                    email = user.email,
                    profileImage = user.profileImage,
                    isOnline = user.isOnline
                )

                transaction.set(
                    document,
                    mapOf(
                        "uid" to dto.uid,
                        "name" to dto.name,
                        "email" to dto.email,
                        "profileImage" to dto.profileImage,
                        "isOnline" to dto.isOnline,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "lastSeen" to null
                    )
                )
            }

            Unit
        }.await()
    }

    override suspend fun getUserProfile(
        uid: String
    ): UserProfile? {
        val snapshot = userCollection.document(uid).get().await()

        if(!snapshot.exists()){
            return null
        }
        return UserProfileDto
            .fromDocument(snapshot)
            .toDomain()
    }

}