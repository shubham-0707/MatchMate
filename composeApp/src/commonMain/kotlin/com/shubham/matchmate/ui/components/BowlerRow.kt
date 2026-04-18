package com.shubham.matchmate.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.BowlerScore

@Composable
fun BowlerRow(bowler: BowlerScore, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = bowler.playerName, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
        Text(text = "${bowler.overs}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.5f))
        Text(text = "${bowler.maidens}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.5f))
        Text(text = "${bowler.runs}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(0.5f))
        Text(text = "${bowler.wickets}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
    }
}
