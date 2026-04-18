package com.shubham.matchmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = CricketGreenLight,
    onPrimary = DarkBackground,
    primaryContainer = CricketGreen,
    onPrimaryContainer = DarkOnBackground,
    secondary = StadiumYellow,
    onSecondary = DarkBackground,
    secondaryContainer = StadiumYellowDark,
    tertiary = SkyBlue,
    onTertiary = DarkBackground,
    error = BallRed,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnBackground,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkOnBackground.copy(alpha = 0.7f),
)

private val LightColorScheme = lightColorScheme(
    primary = CricketGreen,
    onPrimary = LightBackground,
    primaryContainer = CricketGreenLight,
    onPrimaryContainer = LightOnBackground,
    secondary = StadiumYellowDark,
    onSecondary = LightOnBackground,
    secondaryContainer = StadiumYellow,
    tertiary = SkyBlueDark,
    onTertiary = LightBackground,
    error = BallRed,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnBackground,
    surfaceVariant = LightCard,
    onSurfaceVariant = LightOnBackground.copy(alpha = 0.7f),
)

@Composable
fun MatchMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MatchMateTypography,
        content = content
    )
}
