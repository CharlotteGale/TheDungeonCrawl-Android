package com.example.thedungeoncrawl.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.DungeonBlack
import com.example.thedungeoncrawl.ui.theme.DungeonLightGrey
import com.example.thedungeoncrawl.ui.theme.DungeonMidGrey
import com.example.thedungeoncrawl.ui.theme.DungeonRed
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

@Composable
fun HelpScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        innerPadding ->

        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DungeonBlack)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            //Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = onBack,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = DungeonRed),
                    modifier = Modifier.retroBorder()
                ) {
                    Text("◄ Back")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "HELP",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DungeonRed,
                    letterSpacing = 4.sp
                )
            }

            HorizontalDivider(color = DungeonRed, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                //Commands Section
                HelpSection(title = "COMMANDS") {
                    HelpEntry("go <direction>", "Move in a direction — north, south, east, west")
                    HelpEntry("take <item>", "Pick up an item from the room")
                    HelpEntry("drop <item>", "Drop an item from your inventory")
                    HelpEntry("open <target>", "Open a chest or attempt a door")
                    HelpEntry("examine <target>", "Inspect an item, chest or body closely")
                    HelpEntry("loot <target>", "Loot a chest or body for items")
                    HelpEntry("use <item>", "Use an item from your inventory")
                    HelpEntry("inventory", "List everything you're carrying")
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = DungeonMidGrey, thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(16.dp))

                // Mechanics Section
                HelpSection(title = "MECHANICS") {
                    HelpEntry("Chests", "Open a chest first, then examine it to see the contents, then loot to take them.")
                    HelpEntry("Keys", "Keys are used with 'use <key>'. Stand in the right room — a key won't work somewhere it doesn't belong.")
                    HelpEntry("Torches", "Some areas are too dark to search without light. Find a torch and use it.")
                    HelpEntry("Doors", "Some doors are jammed or locked. There's always a reason they won't open.")
                    HelpEntry("Lootables", "Bodies and containers can be looted. Examine them first to see what they're carrying.")
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = DungeonMidGrey, thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(16.dp))

                // Lore Hints Section
                HelpSection(title = "NOTES FROM A PREVIOUS ADVENTURER") {
                    HelpHint("The altar room feels watched. Something about that sigil.")
                    HelpHint("The torches in the corridor don't flicker. Torches always flicker.")
                    HelpHint("I counted the mouse droppings in the mess room. There were none.")
                    HelpHint("Whatever made that footprint in the supply store... it came from inside.")
                    HelpHint("The journal says 'below'. I haven't found the way down yet.")
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

}

@Composable
private fun HelpSection(
    title: String,
    content: @Composable () -> Unit
) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = DungeonRed,
        letterSpacing = 2.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    content()
}

@Composable
private fun HelpEntry(
    command: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = command,
            fontSize = 12.sp,
            color = DungeonLightGrey,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(140.dp)
        )
        Text(
            text = description,
            fontSize = 12.sp,
            color = DungeonMidGrey,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun HelpHint(hint: String) {
    Text(
        text = "— $hint",
        fontSize = 12.sp,
        color = DungeonMidGrey,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    TheDungeonCrawlTheme {
        HelpScreen(onBack = {})
    }
}