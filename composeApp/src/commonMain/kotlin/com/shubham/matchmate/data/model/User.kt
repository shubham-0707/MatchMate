package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FanProfile(
    val uid: String,
    val displayName: String,
    val avatarUrl: String = "",
    val favoriteTeamId: String = "",
    val badges: List<Badge> = emptyList(),
    val matchesFollowed: Int = 0,
    val messagesSent: Int = 0,
    val pollsVoted: Int = 0,
    val joinedAt: String = ""
)

@Serializable
data class Badge(
    val id: String,
    val name: String,
    val iconName: String,
    val description: String,
    val earnedAt: String = ""
)
