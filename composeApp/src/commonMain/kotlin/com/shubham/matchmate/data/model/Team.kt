package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: String,
    val name: String,
    val shortName: String,
    val logoUrl: String = "",
    val primaryColor: Long = 0xFF808080
)

@Serializable
data class Player(
    val id: String,
    val name: String,
    val role: PlayerRole,
    val imageUrl: String = ""
)

@Serializable
enum class PlayerRole {
    BATTER, BOWLER, ALL_ROUNDER, WICKET_KEEPER
}
