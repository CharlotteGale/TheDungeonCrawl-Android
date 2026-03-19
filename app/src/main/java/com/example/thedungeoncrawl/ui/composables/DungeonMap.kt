package com.example.thedungeoncrawl.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.example.thedungeoncrawl.engine.dungeonConnections
import com.example.thedungeoncrawl.engine.dungeonMapLayout
import com.example.thedungeoncrawl.model.RoomMarkers
import com.example.thedungeoncrawl.ui.theme.DungeonBlack
import com.example.thedungeoncrawl.ui.theme.DungeonDarkGrey
import com.example.thedungeoncrawl.ui.theme.DungeonLightGrey
import com.example.thedungeoncrawl.ui.theme.DungeonMidGrey
import com.example.thedungeoncrawl.ui.theme.DungeonRed

@Composable
fun DungeonMap(
    visitedRooms: Set<String>,
    currentRoomId: String,
    roomMarkers: Map<String, RoomMarkers>,
    isMiniMap: Boolean = false,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()
    val cellSize = if (isMiniMap) 40f else 100f
    val padding = if (isMiniMap) 8f else 24f

    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val transformState = rememberTransformableState { zoomChange, panChange, _ ->
        scale = (scale * zoomChange).coerceIn(0.5f, 3f)
        offset += panChange
    }

    Canvas(
        modifier = modifier
            .background(DungeonBlack)
            .then(
                if (!isMiniMap) Modifier.transformable(transformState)
                else Modifier
            )
    ) {
        val drawBlock: DrawScope.() -> Unit = {
            dungeonConnections.forEach { (fromId, toId) ->
                val from = dungeonMapLayout.find { it.id == fromId }
                val to = dungeonMapLayout.find { it.id == toId }
                if (from != null && to != null) {
                    val fromVisited = fromId in visitedRooms
                    val toVisited = toId in visitedRooms
                    if (fromVisited || toVisited) {
                        val fromCentre = Offset(
                            (to.x + to.width / 2) * cellSize + padding,
                            (from.y + from.height / 2) * cellSize + padding
                        )
                        val toCentre = Offset(
                            (to.x + to.width / 2) * cellSize + padding,
                            (from.y + from.height / 2) * cellSize + padding
                        )
                        drawLine(
                            color = DungeonMidGrey,
                            start = fromCentre,
                            end = toCentre,
                            strokeWidth = if (isMiniMap) 1f else 2f
                        )
                    }
                }
            }

            dungeonMapLayout.forEach { room ->
                val isVisited = room.id in visitedRooms
                val isCurrent = room.id == currentRoomId
                val isLocked = room.isLocked

                if (isMiniMap && (isLocked || !isVisited)) return@forEach
                if (!isVisited && !isLocked) return@forEach

                val left = room.x * cellSize + padding
                val top = room.y * cellSize + padding
                val width = room.width * cellSize
                val height = room.height * cellSize

                val fillColor = when {
                    isCurrent -> DungeonRed.copy(alpha = 0.3f)
                    isLocked -> Color.Transparent
                    isVisited -> DungeonDarkGrey
                    else -> Color.Transparent
                }

                drawRect(
                    color = fillColor,
                    topLeft = Offset(left, top),
                    size = Size(width, height)
                )

                val borderColor = when {
                    isCurrent -> DungeonRed
                    isLocked -> DungeonMidGrey
                    else -> DungeonLightGrey.copy(alpha = 0.5f)
                }

                val stroke = if (isLocked) {
                    Stroke(
                        width = 1f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 4f))
                    )
                } else {
                    Stroke(width = if (isCurrent) 2f else 1f)
                }

                drawRect(
                    color = borderColor,
                    topLeft = Offset(left, top),
                    size = Size(width, height),
                    style = stroke
                )

                if (!isMiniMap && isVisited) {
                    val markers = roomMarkers[room.id]

                    if (room.label.isNotEmpty()) {
                        drawText(
                            textMeasurer = textMeasurer,
                            text = room.label,
                            topLeft = Offset(left + 4f, top + 4f),
                            style = TextStyle(
                                color = if (isCurrent) DungeonRed else DungeonLightGrey,
                                fontSize = 8.sp
                            )
                        )
                    }

                    if (markers?.hasChest == true) {
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "X",
                            topLeft = Offset(left + 4f, top + height - 20f),
                            style = TextStyle(color = DungeonRed, fontSize = 10.sp)
                        )
                    }

                    if (markers?.hasLootable == true || markers?.hasItems == true) {
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "T",
                            topLeft = Offset(left + 16f, top + height - 20f),
                            style = TextStyle(color = DungeonLightGrey, fontSize = 10.sp)
                        )
                    }
                }

                if (isMiniMap && isCurrent) {
                    drawCircle(
                        color = DungeonRed,
                        radius = 3f,
                        center = Offset(left + width / 2, top + height / 2)
                    )
                }
            }
        }

        if (!isMiniMap) {
            scale(scale, pivot = center) {
                translate(offset.x, offset.y) {
                    drawBlock()
                }
            }
        } else {
            drawBlock()
        }
    }
}