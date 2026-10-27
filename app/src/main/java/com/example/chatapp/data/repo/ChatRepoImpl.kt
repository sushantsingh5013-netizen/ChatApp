package com.example.chatapp.data.repo

import com.example.chatapp.data.model.ChatDto
import com.example.chatapp.data.model.MessageDto
import com.example.chatapp.domain.model.Chat
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.repo.ChatRepo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepoImpl @Inject constructor(
    private val firestore : FirebaseFirestore
) : ChatRepo{
    override suspend fun getOrCreateChat(
        currentUserId: String,
        otherUserId: String
    ): Result<Chat> {

        return try {
            val chatId = listOf(currentUserId,otherUserId)
                .sorted()
                .joinToString("_")

            val chatRef = firestore
                .collection("chats")
                .document(chatId)

            val chat = firestore.runTransaction {
                transaction ->
                val snapshot = transaction.get(chatRef)

                if (snapshot.exists()){
                    ChatDto.fromDocument(snapshot).toDomain()
                }else{
                    val now = System.currentTimeMillis()

                    val chatData = mapOf(
                        "participantIds" to listOf(
                            currentUserId,
                            otherUserId
                        ).sorted(),
                        "createdAt" to now,
                        "lastMessage" to "",
                        "lastMessageAt" to 0L
                    )
                    transaction.set(chatRef, chatData)

                    Chat(
                        id = chatId,
                        participantIds = listOf(
                            currentUserId,
                            otherUserId
                        ).sorted(),
                        createdAt = now,
                        lastMessage = "",
                        lastMessageAt = 0L
                    )

                }

            }.await()
            Result.success(chat)

        }catch (e : CancellationException){
            throw e
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun sendMessage(
        chatId: String,
        message: Message
    ): Result<Unit> {
        return try {
            val chatRef = firestore
                .collection("chats")
                .document(chatId)

            val messageRef = chatRef
                .collection("messages")
                .document()

            val timestamp = System.currentTimeMillis()

            val messageData = mapOf(
                "senderId" to message.senderId,
                "text" to message.text,
                "timestamp" to timestamp,
                "type" to message.type.name
            )

            val batch = firestore.batch()

            batch.set(messageRef, messageData)

            batch.update(
                chatRef,
                mapOf(
                    "lastMessage" to message.text,
                    "lastMessageAt" to timestamp
                )
            )

            batch.commit().await()

            Result.success(Unit)
        }catch (
            e : CancellationException
        ){
            throw e
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun observeChats(
        currentUserId: String
    ): Flow<Result<List<Chat>>> = callbackFlow {
        val registration = firestore
            .collection("chats")
            .whereArrayContains("participantIds", currentUserId)
            .orderBy("lastMessageAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if(error != null){
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }

                if(snapshot == null){
                    trySend(
                        Result.failure(
                            IllegalArgumentException("Chat snapshot is null")
                        )
                    )
                    return@addSnapshotListener
                }

                try {
                    val chats = snapshot.documents.map {
                        document->
                        ChatDto.fromDocument(document).toDomain()
                    }
                    trySend(Result.success(chats))
                }catch (e : Exception){
                    trySend(Result.failure(e))
                }
            }
        awaitClose{
            registration.remove()
        }
    }

    override fun observeMessages(
        chatId: String
    ): Flow<Result<List<Message>>> = callbackFlow{
        val registration = firestore
            .collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if(error != null){
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }
                if(snapshot == null ){
                    trySend(
                        Result.failure(
                            IllegalArgumentException("Message snapshot is null")
                        )
                    )
                    return@addSnapshotListener
                }
                try {
                    val messages = snapshot.documents.map {
                        document->
                        MessageDto
                            .fromDocument(document, chatId)
                            .toDomain()
                    }
                    trySend(
                        Result.success(messages)
                    )
                }catch (e : Exception){
                    trySend(Result.failure(e))
                }
            }
        awaitClose {
            registration.remove()
        }
    }
}