package com.example.thedungeoncrawl.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedungeoncrawl.model.Item
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

@Composable
fun InventoryPanel(
    inventory: List<Item>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = { isExpanded = !isExpanded },
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .retroBorder()
        ) {
            Text(if (isExpanded) "Inventory ▲" else "Inventory ▼")
        }

        if (isExpanded) {
            if (inventory.isEmpty()) {
                Text(
                    text = "You aren't carrying anything.",
                    modifier = Modifier.padding(8.dp)
                )
            } else {
                inventory.forEach { item ->
                    Text(
                        text = "- ${item.name}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryPanelPreview() {
    TheDungeonCrawlTheme {
        InventoryPanel(
            inventory = listOf(
                Item(id = "torch", name = "Lit Torch", description = ""),
                Item(id = "chest_1_key", name = "Small Key", description = "")
            )
        )
    }
}