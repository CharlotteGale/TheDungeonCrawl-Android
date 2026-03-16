package com.example.thedungeoncrawl.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

@Composable
fun RoomDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    ) {
        Text(
            text = description,
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomDescriptionPreview() {
    TheDungeonCrawlTheme {
        RoomDescription(
            description = "You stand in a dimly lit stone chamber, the air stale and cold against your skin. " +
                    "\nTorch sconces line the walls, their flames long since guttered — all but one, which flickers weakly in a draft you cannot place. " +
                    "\nTo the north, a crumbling archway opens into a vaulted chamber beyond. " +
                    "\nTo the east, a narrow corridor disappears into shadow, the air from it heavy and damp. " +
                    "\nTo the west, a heavy wooden door sits firmly in its frame. " +
                    "\nBehind you to the south, a sliver of daylight cuts through the gloom — the way out."
        )
    }
}