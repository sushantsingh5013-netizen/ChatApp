package com.example.chatapp.data.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import javax.inject.Inject

class GoogleAuthClient @Inject constructor(
    private val credentialManager: CredentialManager
) {
    suspend fun getGoogleIdToken(
        context : Context
    ) : String{
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(
                context.getString(
                    com.example.chatapp.R.string.default_web_client_id
                )
            )
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(
            context = context,
            request = request
        )

        val credential = result.credential

        if(
            credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ){
            return try {
                GoogleIdTokenCredential
                    .createFrom(credential.data)
                    .idToken
            } catch (e : GoogleIdTokenParsingException){
                throw IllegalStateException(
                    "Unable to read Google Id token"
                )
            }
        }

        throw IllegalStateException(
            "Unexpected Google credential type"
        )
    }
}