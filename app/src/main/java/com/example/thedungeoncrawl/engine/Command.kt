package com.example.thedungeoncrawl.engine

sealed class Command {
    data class Go(val direction: String) : Command()
    data class Take(val itemName: String) : Command()
    data class Drop(val itemName: String) : Command()
    data class Open(val target: String) : Command()
    data class Loot(val target: String) : Command()
    data class Examine(val target: String) : Command()
    data class Use(val itemName: String) : Command()
    object Inventory : Command()
    object Help : Command()
    object Quit : Command()
    object Unknown : Command()
}