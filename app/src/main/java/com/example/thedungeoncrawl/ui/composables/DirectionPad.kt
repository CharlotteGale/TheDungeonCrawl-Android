package com.example.thedungeoncrawl.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.DungeonRed
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

@Composable
fun DirectionPad(
    exits: List<String>,
    onDirectionPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        DirectionButton(
            label = "N",
            enabled = exits.contains("north"),
            onClick = { onDirectionPressed("north") }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           DirectionButton(
               label = "W",
               enabled = exits.contains("west"),
               onClick = { onDirectionPressed("west") }
           )
            Spacer(modifier = Modifier.width(48.dp))
            DirectionButton(
                label = "E",
                enabled = exits.contains("east"),
                onClick = { onDirectionPressed("east") }
            )
        }
        DirectionButton(
            label = "S",
            enabled = exits.contains("south"),
            onClick = { onDirectionPressed("south") }
        )
    }
}

@Composable
private fun DirectionButton(
    label: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = DungeonRed,
            disabledContainerColor = Color(0xFF1A1A1A)
        ),
        border = null,
        modifier = Modifier
            .size(48.dp)
            .retroBorder()
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun DirectionPadPreview() {
    TheDungeonCrawlTheme {
        DirectionPad(
            exits = listOf("north", "east"),
            onDirectionPressed = {}
        )
    }
}