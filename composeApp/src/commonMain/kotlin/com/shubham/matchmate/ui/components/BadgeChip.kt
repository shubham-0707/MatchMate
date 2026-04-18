package com.shubham.matchmate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.Badge
import com.shubham.matchmate.ui.theme.StadiumYellow

@Composable
fun BadgeChip(badge: Badge, modifier: Modifier = Modifier) {
    val icon = when (badge.iconName) {
        "star" -> "\u2B50"
        "chat" -> "\uD83D\uDCAC"
        "poll" -> "\uD83D\uDCCA"
        "clock" -> "\u23F0"
        "shield" -> "\uD83D\uDEE1\uFE0F"
        else -> "\uD83C\uDFC6"
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(StadiumYellow.copy(alpha = 0.15f))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = badge.name,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            color = StadiumYellow
        )
    }
}
