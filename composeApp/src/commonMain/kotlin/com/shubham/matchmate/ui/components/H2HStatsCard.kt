package com.shubham.matchmate.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.matchmate.data.model.HeadToHead
import com.shubham.matchmate.data.remote.MockCricketData
import com.shubham.matchmate.ui.theme.*

@Composable
fun H2HStatsCard(
    h2h: HeadToHead,
    modifier: Modifier = Modifier
) {
    val team1 = MockCricketData.teams.find { it.id == h2h.team1Id }
    val team2 = MockCricketData.teams.find { it.id == h2h.team2Id }
    val team1Color = getTeamColor(h2h.team1Id)
    val team2Color = getTeamColor(h2h.team2Id)

    val t1WinPct = if (h2h.totalMatches > 0) h2h.team1Wins.toFloat() / h2h.totalMatches else 0.5f
    val animatedT1Pct by animateFloatAsState(targetValue = t1WinPct, animationSpec = tween(800))

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Teams header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    TeamLogo(teamId = h2h.team1Id, teamName = team1?.shortName ?: "", size = 48.dp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(team1?.shortName ?: "", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${h2h.totalMatches}", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text("matches", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    TeamLogo(teamId = h2h.team2Id, teamName = team2?.shortName ?: "", size = 48.dp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(team2?.shortName ?: "", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Win bar
            Row(modifier = Modifier.fillMaxWidth().height(32.dp).clip(RoundedCornerShape(16.dp))) {
                Box(
                    modifier = Modifier.weight(animatedT1Pct.coerceAtLeast(0.05f)).fillMaxHeight().background(team1Color),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${h2h.team1Wins}W", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                if (h2h.noResults > 0) {
                    Box(
                        modifier = Modifier.weight(h2h.noResults.toFloat() / h2h.totalMatches).fillMaxHeight().background(Color.Gray.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${h2h.noResults}", color = Color.White, fontSize = 10.sp)
                    }
                }
                Box(
                    modifier = Modifier.weight((1f - animatedT1Pct).coerceAtLeast(0.05f)).fillMaxHeight().background(team2Color),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${h2h.team2Wins}W", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats grid
            H2HStatRow("Avg Score", "${h2h.team1AvgScore}", "${h2h.team2AvgScore}", team1Color, team2Color)
            H2HStatRow("Highest", "${h2h.team1HighestScore}", "${h2h.team2HighestScore}", team1Color, team2Color)

            Spacer(modifier = Modifier.height(12.dp))

            // Biggest wins
            Text("Biggest Wins", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(h2h.team1BiggestWin, style = MaterialTheme.typography.bodySmall, color = team1Color)
            Text(h2h.team2BiggestWin, style = MaterialTheme.typography.bodySmall, color = team2Color)

            Spacer(modifier = Modifier.height(12.dp))

            // Last 5 results
            Text("Last 5 Results", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                h2h.lastFiveResults.forEach { result ->
                    val winColor = if (result.winnerId == h2h.team1Id) team1Color else team2Color
                    val winTeam = if (result.winnerId == h2h.team1Id) team1?.shortName else team2?.shortName
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(winColor.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = winTeam ?: "?",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = winColor
                        )
                    }
                }
            }

            // Top performers
            if (h2h.topPerformers.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text("Top Performers", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                h2h.topPerformers.forEach { performer ->
                    val pColor = getTeamColor(performer.teamId)
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text("\u2022 ", color = pColor, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                        Text(performer.playerName, fontWeight = FontWeight.Medium, style = MaterialTheme.typography.bodySmall, color = pColor)
                        Text(" \u2014 ${performer.stat}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

@Composable
private fun H2HStatRow(label: String, value1: String, value2: String, color1: Color, color2: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(value1, fontWeight = FontWeight.Bold, color = color1, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(value2, fontWeight = FontWeight.Bold, color = color2, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
    }
}
