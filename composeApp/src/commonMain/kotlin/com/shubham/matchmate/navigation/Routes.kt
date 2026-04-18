package com.shubham.matchmate.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable data object Splash : Route
    @Serializable data object Login : Route
    @Serializable data object SignUp : Route
    @Serializable data object TeamSelection : Route
    @Serializable data object Home : Route
    @Serializable data class MatchDetail(val matchId: String) : Route
    @Serializable data object Profile : Route
    @Serializable data class PostMatchThreads(val matchId: String) : Route
    @Serializable data class ThreadDetail(val threadId: String, val matchId: String) : Route
    @Serializable data object Settings : Route
    @Serializable data class MatchTimeline(val matchId: String) : Route
    @Serializable data class Leaderboard(val matchId: String? = null) : Route
    @Serializable data class Predictions(val matchId: String) : Route
    @Serializable data class HeadToHead(val team1Id: String, val team2Id: String) : Route
}
