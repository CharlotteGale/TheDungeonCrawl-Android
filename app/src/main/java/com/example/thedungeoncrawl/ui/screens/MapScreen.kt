package com.example.thedungeoncrawl.ui.screens

import android.R
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thedungeoncrawl.ui.composables.DungeonMap
import com.example.thedungeoncrawl.ui.retroBorder
import com.example.thedungeoncrawl.ui.theme.*

@Composable
fun MapScreen(
    visitedRooms: Set<String>,
    currentRoomId: String,
    roomMarkers: Map<String, RoomMarkers>,
    hasMap: Boolean,
    onBack: () -> Unit,
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
                .padding(16.dp)
        ) {
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
                    text = "MAP",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DungeonRed,
                    letterSpacing = 4.sp
                )
            }

            HorizontalDivider(color = DungeonRed, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            
            if (!hasMap) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "You don't have a map of this place... yet.",
                        color = DungeonMidGrey,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                DungeonMap(
                    visitedRooms = visitedRooms,
                    currentRoomId = currentRoomId,
                    roomMarkers = roomMarkers,
                    isMiniMap = false,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}