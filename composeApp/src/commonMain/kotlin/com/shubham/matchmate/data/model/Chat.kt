package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val id: String,
    val matchId: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String = "",
    val userTeamId: String = "",
    val message: String,
    val timestamp: String,
    val reactionCounts: Map<String, Int> = emptyMap()
)
