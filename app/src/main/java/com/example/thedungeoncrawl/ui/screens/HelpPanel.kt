package com.example.thedungeoncrawl.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.DungeonLightGrey
import com.example.thedungeoncrawl.ui.theme.DungeonMidGrey
import com.example.thedungeoncrawl.ui.theme.DungeonRed


@Composable
fun HelpPanel(
    onFullHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { isExpanded = !isExpanded },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = DungeonRed),
                modifier = Modifier
                    .weight(1f)
                    .retroBorder()
            ) {
                Text(if (isExpanded) "Help ▲" else "Help ▼")
            }
            Button(
                onClick = onFullHelp,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = DungeonRed),
                modifier = Modifier.retroBorder()
            ) {
                Text("?")
            }
        }
        if (isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("go <direction>     — move north, south, east, west",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("take <item>        — pick up an item",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("drop <item>        — drop an item",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("open <target>      — open a chest or door",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("examine <target>   — inspect something",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("loot <target>      — loot a chest or body",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("use <item>         — use an item",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Text("inventory          — list what you're carrying",
                    fontSize = 12.sp, color = DungeonLightGrey)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tap ? for full help",
                    fontSize = 11.sp,
                    color = DungeonMidGrey,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}