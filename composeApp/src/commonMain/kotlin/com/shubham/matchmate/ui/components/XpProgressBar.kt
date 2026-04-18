package com.shubham.matchmate.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.FanLevel
import com.shubham.matchmate.data.model.displayName
import com.shubham.matchmate.data.model.emoji
import com.shubham.matchmate.data.model.nextLevel
import com.shubham.matchmate.data.model.xpThreshold
import com.shubham.matchmate.ui.theme.CricketGreenLight
import com.shubham.matchmate.ui.theme.StadiumYellow

@Composable
fun XpProgressBar(
    currentXp: Int,
    level: FanLevel,
    modifier: Modifier = Modifier
) {
    val nextLvl = level.nextLevel()
    val currentThreshold = level.xpThreshold()
    val nextThreshold = nextLvl?.xpThreshold() ?: (currentThreshold + 1000)
    val progress = ((currentXp - currentThreshold).toFloat() / (nextThreshold - currentThreshold)).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(targetValue = progress, animationSpec = tween(800))

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${level.emoji()} ${level.displayName()}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = StadiumYellow
                )
            }
            if (nextLvl != null) {
                Text(
                    text = "${currentXp}/${nextThreshold} XP",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    text = "${currentXp} XP \u2022 MAX LEVEL",
                    style = MaterialTheme.typography.labelSmall,
                    color = StadiumYellow,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp))
                    .background(CricketGreenLight)
            )
        }
        if (nextLvl != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Next: ${nextLvl.emoji()} ${nextLvl.displayName()}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
    }
}
