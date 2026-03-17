package com.example.thedungeoncrawl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thedungeoncrawl.ui.screens.GameScreen
import com.example.thedungeoncrawl.ui.screens.SplashScreen
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDungeonCrawlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SplashScreen(
                        onEnterDungeon = {},
                        modifier = Modifier.padding(innerPadding)
                    )
//                    GameScreen(
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}
