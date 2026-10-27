package com.example.chatapp.data.repo

import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.repo.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val firebaseAuth : FirebaseAuth
) : AuthRepo{
    override suspend fun signInWithGoogle(idToken: String): Result<User> {
        return try {
            val credential = GoogleAuthProvider.getCredential(
                idToken,
                null
            )

            val authResult = firebaseAuth
                .signInWithCredential(credential)
                .await()

            val firebaseUser = authResult.user?: throw IllegalStateException("Firebase user is null")

            Result.success(
                User(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName.orEmpty(),
                    email = firebaseUser.email.orEmpty(),
                    profileImage = firebaseUser.photoUrl?.toString()
                )
            )

        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): User? {
        return firebaseAuth.currentUser?.toDomainUser()
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<User> = runCatching {
        val authResult = firebaseAuth
            .signInWithEmailAndPassword(email,password)
            .await()

        val firebaseUser = requireNotNull(
            authResult.user
        ){
            "Authentication succeeded without a user"
        }

        firebaseUser.toDomainUser()
    }

    override suspend fun registerWithEmail(
        name : String,
        email: String,
        password: String
    ): Result<User> = try {

        val authResult = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()

        val firebaseUser = authResult.user ?:
            throw IllegalStateException("User creation failed")

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name.trim())
            .build()
        firebaseUser.updateProfile(profileUpdates).await()
        Result.success(firebaseUser.toDomainUser())

    }catch (e : Exception){
        Result.failure(e)
    }

    private fun FirebaseUser.toDomainUser() = User(
        uid = uid,
        name = displayName.orEmpty(),
        email = email.orEmpty(),
        profileImage = photoUrl?.toString().orEmpty(),
        isOnline = false,
        lastSeen = 0L
    )

}