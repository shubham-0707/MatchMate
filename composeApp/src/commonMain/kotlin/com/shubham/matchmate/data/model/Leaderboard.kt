package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardEntry(
    val rank: Int,
    val userId: String,
    val userName: String,
    val teamId: String,
    val xp: Int,
    val level: FanLevel,
    val matchesFollowed: Int,
    val messagesSent: Int,
    val correctPredictions: Int,
    val isCurrentUser: Boolean = false
)

@Serializable
enum class FanLevel {
    NEWCOMER, FAN, SUPER_FAN, ULTRA_FAN, LEGEND
}

fun FanLevel.displayName(): String = when (this) {
    FanLevel.NEWCOMER -> "Newcomer"
    FanLevel.FAN -> "Fan"
    FanLevel.SUPER_FAN -> "Super Fan"
    FanLevel.ULTRA_FAN -> "Ultra Fan"
    FanLevel.LEGEND -> "Legend"
}

fun FanLevel.emoji(): String = when (this) {
    FanLevel.NEWCOMER -> "\uD83C\uDF31"
    FanLevel.FAN -> "\u2B50"
    FanLevel.SUPER_FAN -> "\uD83D\uDD25"
    FanLevel.ULTRA_FAN -> "\uD83D\uDC8E"
    FanLevel.LEGEND -> "\uD83D\uDC51"
}

fun FanLevel.xpThreshold(): Int = when (this) {
    FanLevel.NEWCOMER -> 0
    FanLevel.FAN -> 100
    FanLevel.SUPER_FAN -> 500
    FanLevel.ULTRA_FAN -> 1500
    FanLevel.LEGEND -> 5000
}

fun FanLevel.nextLevel(): FanLevel? = when (this) {
    FanLevel.NEWCOMER -> FanLevel.FAN
    FanLevel.FAN -> FanLevel.SUPER_FAN
    FanLevel.SUPER_FAN -> FanLevel.ULTRA_FAN
    FanLevel.ULTRA_FAN -> FanLevel.LEGEND
    FanLevel.LEGEND -> null
}
