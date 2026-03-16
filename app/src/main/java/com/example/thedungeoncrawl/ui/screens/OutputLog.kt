package com.example.thedungeoncrawl.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

@Composable
fun OutputLog(
    messages: List<String>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }
    val scrollState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty() && isExpanded) {
            scrollState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = { isExpanded = !isExpanded },
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .retroBorder()
        ) {
            Text(if (isExpanded) "Log ▲" else "Log ▼")
        }

        if (isExpanded) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp)
            ) {
                items(messages) { message ->
                    Text(
                        text = message,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    HorizontalDivider(thickness = 0.5.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutputLogPreview() {
    TheDungeonCrawlTheme {
        OutputLog(
            messages = listOf(
                "You pick up the Torch.",
                "You can't go that way.",
                "The key turns smoothly. The chest unlocks with a heavy clunk.",
                "You aren't carrying anything."
            )
        )
    }
}