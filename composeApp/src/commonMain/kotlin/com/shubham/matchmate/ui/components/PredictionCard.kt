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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.PredictionQuestion
import com.shubham.matchmate.data.model.label
import com.shubham.matchmate.data.model.points
import com.shubham.matchmate.ui.theme.CricketGreenLight
import com.shubham.matchmate.ui.theme.StadiumYellow

@Composable
fun PredictionCard(
    question: PredictionQuestion,
    userChoice: String?,
    onPredict: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasPredicted = userChoice != null

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header with type badge and points
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = question.type.label(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(StadiumYellow.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "+${question.type.points()} pts",
                        style = MaterialTheme.typography.labelSmall,
                        color = StadiumYellow,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = question.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Options
            question.options.forEach { option ->
                val isSelected = option == userChoice
                val totalVotes = question.totalPredictions + (if (hasPredicted) 1 else 0)
                val optionVotes = (question.predictionBreakdown[option] ?: 0) + (if (isSelected) 1 else 0)
                val percentage = if (totalVotes > 0) optionVotes.toFloat() / totalVotes else 0f

                val animatedPercentage by animateFloatAsState(
                    targetValue = if (hasPredicted) percentage else 0f,
                    animationSpec = tween(600)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable(enabled = !hasPredicted) { onPredict(option) }
                ) {
                    // Progress bar
                    if (hasPredicted) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animatedPercentage)
                                .height(42.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isSelected) CricketGreenLight.copy(alpha = 0.25f)
                                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isSelected) {
                                Text("\u2705 ", style = MaterialTheme.typography.bodySmall)
                            }
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                        if (hasPredicted) {
                            Text(
                                text = "${(animatedPercentage * 100).toInt()}%",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) CricketGreenLight else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${question.totalPredictions + (if (hasPredicted) 1 else 0)} fans predicted",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
