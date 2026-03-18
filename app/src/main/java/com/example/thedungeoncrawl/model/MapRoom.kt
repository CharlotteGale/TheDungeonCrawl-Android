package com.example.thedungeoncrawl.model

data class MapRoom(
    val id: String,
    val label: String,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    var isLocked: Boolean = false,
    val isCorridor: Boolean = false
)
