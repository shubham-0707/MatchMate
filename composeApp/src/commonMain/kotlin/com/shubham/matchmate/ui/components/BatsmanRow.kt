package com.shubham.matchmate.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.BatsmanScore

@Composable
fun BatsmanRow(batsman: BatsmanScore, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (batsman.isOnStrike) "${batsman.playerName} *" else batsman.playerName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (batsman.isOnStrike) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.weight(1.5f)
        )
        Text(text = "${batsman.runs}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
        Text(text = "${batsman.balls}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(0.5f))
        Text(text = "${batsman.fours}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(0.5f))
        Text(text = "${batsman.sixes}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(0.5f))
    }
}
