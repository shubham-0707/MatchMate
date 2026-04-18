package com.shubham.matchmate.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatInput(
    onSendMessage: (String) -> Unit,
    onReaction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val reactions = listOf("\uD83D\uDD25", "\uD83C\uDF89", "\uD83D\uDE22", "\uD83D\uDC4F", "\uD83D\uDC80", "\uD83C\uDFCF")

    Column(modifier = modifier.fillMaxWidth()) {
        // Quick reactions bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            reactions.forEach { emoji ->
                TextButton(
                    onClick = { onReaction(emoji) },
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Text(text = emoji, fontSize = 22.sp)
                }
            }
        }

        // Message input
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") },
                shape = RoundedCornerShape(24.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        onSendMessage(text.trim())
                        text = ""
                    }
                },
                shape = RoundedCornerShape(24.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text("Send")
            }
        }
    }
}
