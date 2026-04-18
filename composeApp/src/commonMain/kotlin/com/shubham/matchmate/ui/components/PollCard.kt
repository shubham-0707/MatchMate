package com.shubham.matchmate.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.Poll

@Composable
fun PollCard(
    poll: Poll,
    onVote: (pollId: String, optionId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var votedOptionId by remember { mutableStateOf<String?>(null) }
    val hasVoted = votedOptionId != null

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = poll.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            poll.options.forEach { option ->
                val totalVotes = if (hasVoted) poll.totalVotes + 1 else poll.totalVotes
                val optionVotes = if (option.id == votedOptionId) option.votes + 1 else option.votes
                val percentage = if (totalVotes > 0) optionVotes.toFloat() / totalVotes else 0f

                val animatedPercentage by animateFloatAsState(
                    targetValue = if (hasVoted) percentage else 0f,
                    animationSpec = tween(600)
                )

                val isSelected = option.id == votedOptionId

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable(enabled = !hasVoted) {
                            votedOptionId = option.id
                            onVote(poll.id, option.id)
                        }
                ) {
                    // Progress bar
                    if (hasVoted) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animatedPercentage)
                                .height(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option.text,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                        if (hasVoted) {
                            Text(
                                text = "${(animatedPercentage * 100).toInt()}%",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${if (hasVoted) poll.totalVotes + 1 else poll.totalVotes} votes",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
