package com.shubham.matchmate.ui.theme

import androidx.compose.ui.graphics.Color

// Primary — Cricket pitch green
val CricketGreen = Color(0xFF1B5E20)
val CricketGreenLight = Color(0xFF4CAF50)
val CricketGreenDark = Color(0xFF0D3B0E)

// Secondary — Stadium accent
val StadiumYellow = Color(0xFFFFC107)
val StadiumYellowDark = Color(0xFFFF8F00)

// Tertiary
val SkyBlue = Color(0xFF0288D1)
val SkyBlueDark = Color(0xFF01579B)

// Error / Live indicator
val BallRed = Color(0xFFD32F2F)
val LiveRed = Color(0xFFFF1744)

// Surfaces
val DarkBackground = Color(0xFF0F0F1A)
val DarkSurface = Color(0xFF1A1A2E)
val DarkCard = Color(0xFF1E1E2E)
val LightBackground = Color(0xFFF5F5F0)
val LightSurface = Color(0xFFFFFFFF)
val LightCard = Color(0xFFF0F0E8)

// Text
val DarkOnBackground = Color(0xFFE8E8E8)
val LightOnBackground = Color(0xFF1A1A1A)

// Per-IPL-team colors
val TeamColors = mapOf(
    "mi" to Color(0xFF004BA0),
    "csk" to Color(0xFFFFC107),
    "rcb" to Color(0xFFD4213D),
    "kkr" to Color(0xFF3A225D),
    "dc" to Color(0xFF004C93),
    "srh" to Color(0xFFFF822A),
    "pbks" to Color(0xFFED1B24),
    "rr" to Color(0xFFEA1A85),
    "gt" to Color(0xFF1C1C2B),
    "lsg" to Color(0xFF00A3E0),
)

fun getTeamColor(teamId: String): Color = TeamColors[teamId] ?: CricketGreen
