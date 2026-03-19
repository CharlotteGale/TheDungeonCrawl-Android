package com.example.thedungeoncrawl.ui.screens

import androidx.compose.foundation.background
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.thedungeoncrawl.ui.composables.CommandInput
import com.example.thedungeoncrawl.ui.composables.DirectionPad
import com.example.thedungeoncrawl.ui.composables.DungeonMap
import com.example.thedungeoncrawl.ui.composables.HelpPanel
import com.example.thedungeoncrawl.ui.composables.InventoryPanel
import com.example.thedungeoncrawl.ui.composables.OutputLog
import com.example.thedungeoncrawl.ui.composables.RoomDescription
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.DungeonBlack
import com.example.thedungeoncrawl.ui.theme.DungeonMidGrey
import com.example.thedungeoncrawl.ui.theme.DungeonRed
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme
import com.example.thedungeoncrawl.viewmodel.GameViewModel


@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel(),
    onHelpPressed: () -> Unit = {},
    onMapPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DungeonBlack)
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            HelpPanel(
                onFullHelp = onHelpPressed,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            HorizontalDivider(
                color = DungeonMidGrey,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            RoomDescription(
                description = viewModel.roomDescription,
                modifier = Modifier.weight(0.35f)
            )

            HorizontalDivider(
                color = DungeonMidGrey,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            OutputLog(
                messages = viewModel.outputLog,
                modifier = Modifier.weight(0.25f)
            )

            HorizontalDivider(
                color = DungeonMidGrey,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            InventoryPanel(
                inventory = viewModel.inventory,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            HorizontalDivider(
                color = DungeonMidGrey,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    DungeonMap(
                        visitedRooms = viewModel.visitedRooms,
                        currentRoomId = viewModel.currentRoomId,
                        roomMarkers = viewModel.roomMarkers,
                        isMiniMap = true,
                        modifier = Modifier
                            .size(150.dp)
                            .retroBorder()
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        onClick = onMapPressed,
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = DungeonRed),
                        contentPadding = PaddingValues(4.dp),
                        modifier = Modifier
                            .width(32.dp)
                            .height(150.dp)
                            .retroBorder()
                    ) {
                        Text(
                            text = "M\nA\nP\n►",
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            lineHeight = 14.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.width(32.dp))
                DirectionPad(
                    exits = viewModel.exits,
                    onDirectionPressed = { direction ->
                        viewModel.onCommand("go $direction")
                    }
                )
            }

            HorizontalDivider(
                color = DungeonMidGrey,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            CommandInput(
                onCommandSent = { input ->
                    viewModel.onCommand(input)
                },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    TheDungeonCrawlTheme {
        GameScreen()
    }
}