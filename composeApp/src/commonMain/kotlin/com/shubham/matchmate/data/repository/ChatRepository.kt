package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.ChatMessage
import com.shubham.matchmate.data.remote.MockCricketData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface ChatRepository {
    fun getMessages(matchId: String): Flow<List<ChatMessage>>
    suspend fun sendMessage(matchId: String, message: String, userName: String, userTeamId: String)
}

class MockChatRepository : ChatRepository {
    private val messagesFlow = MutableStateFlow(MockCricketData.chatMessages.toMutableList().toList())
    private var nextId = 100

    override fun getMessages(matchId: String): Flow<List<ChatMessage>> =
        messagesFlow.map { messages ->
            messages.filter { it.matchId == matchId }.sortedBy { it.timestamp }
        }

    override suspend fun sendMessage(matchId: String, message: String, userName: String, userTeamId: String) {
        val newMsg = ChatMessage(
            id = "c${nextId++}",
            matchId = matchId,
            userId = "current_user",
            userName = userName,
            userTeamId = userTeamId,
            message = message,
            timestamp = "2026-04-18T${(20..23).random()}:${(10..59).random()}:00+05:30"
        )
        messagesFlow.value = messagesFlow.value + newMsg
    }
}
