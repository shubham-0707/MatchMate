package com.shubham.matchmate.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.matchmate.data.model.EventType
import com.shubham.matchmate.data.model.MatchEvent
import com.shubham.matchmate.data.model.emoji
import com.shubham.matchmate.data.model.isHighlight
import com.shubham.matchmate.ui.theme.*

@Composable
fun TimelineEventCard(
    event: MatchEvent,
    modifier: Modifier = Modifier
) {
    val isHighlight = event.type.isHighlight()
    val bgColor = when (event.type) {
        EventType.SIX -> StadiumYellow.copy(alpha = 0.12f)
        EventType.FOUR -> CricketGreenLight.copy(alpha = 0.12f)
        EventType.WICKET -> BallRed.copy(alpha = 0.12f)
        EventType.FIFTY, EventType.CENTURY -> StadiumYellow.copy(alpha = 0.15f)
        EventType.FREE_HIT -> SkyBlue.copy(alpha = 0.12f)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    val accentColor = when (event.type) {
        EventType.SIX -> StadiumYellow
        EventType.FOUR -> CricketGreenLight
        EventType.WICKET -> BallRed
        EventType.FIFTY, EventType.CENTURY -> StadiumYellow
        EventType.FREE_HIT -> SkyBlue
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Timeline dot and line
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            // Over number
            Text(
                text = if (event.over > 0) "${event.over}" else "",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp
            )
            Box(
                modifier = Modifier
                    .size(if (isHighlight) 28.dp else 20.dp)
                    .clip(CircleShape)
                    .background(if (isHighlight) accentColor else accentColor.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = event.type.emoji(),
                    fontSize = if (isHighlight) 14.sp else 10.sp
                )
            }
        }

        // Event card
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = bgColor),
            elevation = if (isHighlight) CardDefaults.cardElevation(defaultElevation = 2.dp) else CardDefaults.cardElevation()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.title,
                        style = if (isHighlight) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodySmall,
                        fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Medium,
                        color = if (isHighlight) accentColor else MaterialTheme.colorScheme.onSurface
                    )
                    if (event.runs > 0) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(accentColor.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "+${event.runs}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = accentColor
                            )
                        }
                    }
                }
                if (event.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                if (event.playerName.isNotEmpty()) {
                    Text(
                        text = event.playerName,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium,
                        color = accentColor.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
