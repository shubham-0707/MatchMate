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
}
