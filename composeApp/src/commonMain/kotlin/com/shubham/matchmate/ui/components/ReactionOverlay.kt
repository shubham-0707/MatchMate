package com.shubham.matchmate.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow

data class FloatingEmoji(val emoji: String, val id: Long, val xOffset: Float)

@Composable
fun ReactionOverlay(
    reactionBurst: SharedFlow<String>,
    modifier: Modifier = Modifier
) {
    var emojis by remember { mutableStateOf(listOf<FloatingEmoji>()) }
    var nextId by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        reactionBurst.collect { emoji ->
            val id = nextId++
            val xOffset = (-100..100).random().toFloat()
            emojis = emojis + FloatingEmoji(emoji, id, xOffset)
            // Remove after animation
            delay(2000)
            emojis = emojis.filter { it.id != id }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        emojis.forEach { floatingEmoji ->
            key(floatingEmoji.id) {
                AnimatedEmoji(
                    emoji = floatingEmoji.emoji,
                    xOffset = floatingEmoji.xOffset,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
private fun AnimatedEmoji(emoji: String, xOffset: Float, modifier: Modifier = Modifier) {
    val animatable = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
        )
    }

    val progress = animatable.value
    val yOffset = -300f * progress
    val alpha = 1f - progress

    Box(
        modifier = modifier
            .offset(x = xOffset.dp, y = yOffset.dp)
            .alpha(alpha)
    ) {
        Text(text = emoji, fontSize = 32.sp)
    }
}
