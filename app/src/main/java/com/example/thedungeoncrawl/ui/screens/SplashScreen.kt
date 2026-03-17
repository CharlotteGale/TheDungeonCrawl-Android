package com.example.thedungeoncrawl.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.DungeonBlack
import com.example.thedungeoncrawl.ui.theme.DungeonLightGrey
import com.example.thedungeoncrawl.ui.theme.DungeonRed
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onEnterDungeon: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fullText = "The entrance looms before you. Torchlight flickers somewhere within.\n\nYou probably shouldn't go in.\n\nYou go in anyway."
    var displayedText by remember { mutableStateOf("") }
    var isComplete by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000L)
        showTitle = true
        delay(500L)

        fullText.forEachIndexed { index, _ ->
            displayedText = fullText.substring(0, index) + "|"
            delay(50L)
            displayedText = fullText.substring(0, index + 1) + "|"
            delay(100L)
        }
        displayedText = fullText.trimEnd('|')
        isComplete = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(DungeonBlack)
            .padding(32.dp)
    ) {
        AnimatedVisibility(
            visible = showTitle,
            enter = fadeIn(animationSpec = tween(1000))
        ) {
            Text(
                text = "THE DUNGEON CRAWL",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DungeonRed,
                textAlign = TextAlign.Center,
                letterSpacing = 4.sp,
                modifier = Modifier.padding(bottom = 48.dp)
            )
        }

        Text(
            text = displayedText,
            fontSize = 16.sp,
            color = DungeonLightGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 64.dp)
                .heightIn(min = 120.dp)
        )

        AnimatedVisibility(
            visible = isComplete,
            enter = fadeIn(animationSpec = tween(1000))
        ) {
            Button(
                onClick = onEnterDungeon,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = DungeonRed
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .retroBorder()
            ) {
                Text(
                    text = "ENTER THE DUNGEON",
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TheDungeonCrawlTheme {
        SplashScreen(onEnterDungeon = {})
    }
}