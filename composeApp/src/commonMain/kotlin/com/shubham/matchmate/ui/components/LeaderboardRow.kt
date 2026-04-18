package com.shubham.matchmate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.matchmate.data.model.LeaderboardEntry
import com.shubham.matchmate.data.model.displayName
import com.shubham.matchmate.data.model.emoji
import com.shubham.matchmate.ui.theme.*

@Composable
fun LeaderboardRow(
    entry: LeaderboardEntry,
    modifier: Modifier = Modifier
) {
    val rankColor = when (entry.rank) {
        1 -> Color(0xFFFFD700) // Gold
        2 -> Color(0xFFC0C0C0) // Silver
        3 -> Color(0xFFCD7F32) // Bronze
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    val cardColor = if (entry.isCurrentUser)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    else
        MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        border = if (entry.isCurrentUser) CardDefaults.outlinedCardBorder() else null
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        if (entry.rank <= 3) rankColor.copy(alpha = 0.2f)
                        else MaterialTheme.colorScheme.surface
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (entry.rank <= 3) when (entry.rank) {
                        1 -> "\uD83E\uDD47"
                        2 -> "\uD83E\uDD48"
                        else -> "\uD83E\uDD49"
                    } else "#${entry.rank}",
                    fontSize = if (entry.rank <= 3) 18.sp else 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = rankColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Avatar
            TeamLogo(teamId = entry.teamId, teamName = entry.userName, size = 36.dp)

            Spacer(modifier = Modifier.width(12.dp))

            // Name + level
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = entry.userName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (entry.isCurrentUser) FontWeight.Bold else FontWeight.Medium
                    )
                    if (entry.isCurrentUser) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "(You)",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${entry.level.emoji()} ${entry.level.displayName()}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // XP
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${entry.xp}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = StadiumYellow
                )
                Text(
                    text = "XP",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
