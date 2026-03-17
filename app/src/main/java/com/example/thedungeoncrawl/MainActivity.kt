package com.example.thedungeoncrawl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thedungeoncrawl.ui.screens.GameScreen
import com.example.thedungeoncrawl.ui.screens.SplashScreen
import com.example.thedungeoncrawl.ui.theme.TheDungeonCrawlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDungeonCrawlTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(
                            onEnterDungeon = {
                                navController.navigate("game") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("game") {
                        GameScreen()
                    }
                }
            }
        }
    }
}
