package com.example.thedungeoncrawl.ui.screens

import androidx.compose.foundation.background
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.thedungeoncrawl.ui.composables.CommandInput
import com.example.thedungeoncrawl.ui.composables.DirectionPad
import com.example.thedungeoncrawl.ui.composables.HelpPanel
import com.example.thedungeoncrawl.ui.composables.InventoryPanel
import com.example.thedungeoncrawl.ui.composables.OutputLog
import com.example.thedungeoncrawl.ui.composables.RoomDescription
import com.example.thedungeoncrawl.ui.theme.DungeonBlack
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme
import com.example.thedungeoncrawl.viewmodel.GameViewModel


@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel(),
    onHelpPressed: () -> Unit = {},
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
            RoomDescription(
                description = viewModel.roomDescription,
                modifier = Modifier.weight(0.35f)
            )

            HorizontalDivider()

            OutputLog(
                messages = viewModel.outputLog,
                modifier = Modifier.weight(0.25f)
            )

            HorizontalDivider()

            InventoryPanel(
                inventory = viewModel.inventory,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            HorizontalDivider()

            DirectionPad(
                exits = viewModel.exits,
                onDirectionPressed = { direction ->
                    viewModel.onCommand("go $direction")
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            HorizontalDivider()

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