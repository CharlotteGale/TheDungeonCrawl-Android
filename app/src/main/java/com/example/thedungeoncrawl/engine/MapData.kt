package com.example.thedungeoncrawl.engine

import com.example.thedungeoncrawl.model.MapRoom

val dungeonMapLayout = listOf(
    MapRoom("entrance",     "ENTRANCE",      2f, 2f, 2f,   1.5f),
    MapRoom("altar_room",   "ALTAR ROOM",    2f, 0.5f, 2f, 1.5f),
    MapRoom("mess_room",    "MESS ROOM",     0f, 2f, 2f,   1.5f),
    MapRoom("corridor",     "EAST CORRIDOR", 4f, 2f, 1.5f, 0.4f, isCorridor = true),
    MapRoom("barracks",     "BARRACKS",      5.5f, 1.5f, 2f, 1.5f),
    MapRoom("exit", "EXIT", 2f, 3.5f, 1f, 1f),
    MapRoom("supply_store", "SUPPLY ROOM",   0f, 3.5f, 2f, 1f),
    MapRoom("north_corridor", "NORTH CORRIDOR", 0f, 0f, 0.4f, 1.5f, isLocked = true, isCorridor = true),
    )

val dungeonConnections = listOf(
    "entrance" to "altar_room",
    "entrance" to "mess_room",
    "entrance" to "corridor",
    "entrance" to "exit",
    "mess_room" to "supply_store",
    "corridor" to "barracks",
)