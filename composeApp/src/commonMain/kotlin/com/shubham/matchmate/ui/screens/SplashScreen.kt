package com.shubham.matchmate.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.matchmate.ui.theme.*
import com.shubham.matchmate.viewmodel.AuthState
import com.shubham.matchmate.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    // Navigation after delay
    LaunchedEffect(Unit) {
        delay(3000)
        when (viewModel.authState.value) {
            is AuthState.Authenticated -> onNavigateToHome()
            else -> onNavigateToLogin()
        }
    }

    // Animations
    val infiniteTransition = rememberInfiniteTransition()

    // Cricket ball rotation
    val ballRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Pulsing glow
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Staggered entry animations
    var started by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { started = true }

    val logoScale by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 300f)
    )
    val titleAlpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(800, delayMillis = 500)
    )
    val subtitleAlpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(800, delayMillis = 900)
    )
    val bottomAlpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(800, delayMillis = 1300)
    )

    // Background gradient
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CricketGreenDark,
                        DarkBackground,
                        DarkSurface
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Animated background circles (stadium lights effect)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height * 0.35f)
            // Outer glow ring
            drawCircle(
                color = CricketGreenLight.copy(alpha = glowAlpha * 0.15f),
                radius = 280f,
                center = center
            )
            drawCircle(
                color = CricketGreenLight.copy(alpha = glowAlpha * 0.08f),
                radius = 400f,
                center = center
            )
            // Seam lines rotating (like a cricket ball)
            drawCircle(
                color = StadiumYellow.copy(alpha = glowAlpha * 0.1f),
                radius = 180f,
                center = center,
                style = Stroke(width = 2f)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.3f))

            // Logo — Cricket ball with bat
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .scale(logoScale),
                contentAlignment = Alignment.Center
            ) {
                // Glow behind ball
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .alpha(glowAlpha * 0.5f)
                        .blur(20.dp)
                        .clip(CircleShape)
                        .background(CricketGreenLight)
                )
                // Cricket ball body
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFCC2200),
                                    Color(0xFF8B0000)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Seam line
                    Canvas(modifier = Modifier.size(110.dp).rotate(ballRotation)) {
                        val seamColor = Color(0xFFFFF8E1)
                        // Vertical seam
                        drawLine(
                            color = seamColor,
                            start = Offset(size.width * 0.5f, size.height * 0.1f),
                            end = Offset(size.width * 0.5f, size.height * 0.9f),
                            strokeWidth = 3f
                        )
                        // Stitch marks
                        for (i in 1..6) {
                            val y = size.height * (0.15f + i * 0.11f)
                            drawLine(
                                color = seamColor,
                                start = Offset(size.width * 0.38f, y),
                                end = Offset(size.width * 0.48f, y),
                                strokeWidth = 2f
                            )
                            drawLine(
                                color = seamColor,
                                start = Offset(size.width * 0.52f, y),
                                end = Offset(size.width * 0.62f, y),
                                strokeWidth = 2f
                            )
                        }
                    }
                }
                // Bat overlay at bottom-right
                Text(
                    text = "\uD83C\uDFCF",
                    fontSize = 36.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 8.dp, y = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // App name
            Text(
                text = "MatchMate",
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp,
                modifier = Modifier.alpha(titleAlpha)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = "Connect \u2022 Cheer \u2022 Celebrate",
                style = MaterialTheme.typography.bodyLarge,
                color = CricketGreenLight,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp,
                modifier = Modifier.alpha(subtitleAlpha)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Your cricket fan community",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.5f),
                modifier = Modifier.alpha(subtitleAlpha)
            )

            Spacer(modifier = Modifier.weight(0.5f))

            // Bottom loading indicator
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(bottomAlpha)
            ) {
                // Animated dots
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(3) { index ->
                        val dotAlpha by infiniteTransition.animateFloat(
                            initialValue = 0.3f,
                            targetValue = 1f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(600, delayMillis = index * 200),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .alpha(dotAlpha)
                                .clip(CircleShape)
                                .background(CricketGreenLight)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Season badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.08f))
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "\uD83C\uDFC6 IPL 2026 Season",
                        style = MaterialTheme.typography.labelMedium,
                        color = StadiumYellow,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
