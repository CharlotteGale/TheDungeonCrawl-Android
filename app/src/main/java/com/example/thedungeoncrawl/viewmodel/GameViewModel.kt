package com.example.thedungeoncrawl.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.thedungeoncrawl.engine.CommandParser
import com.example.thedungeoncrawl.engine.GameEngine

class GameViewModel : ViewModel() {
    private val engine = GameEngine()

    var roomDescription by mutableStateOf(engine.currentRoom().description)
        private set

    var outputLog by mutableStateOf(listOf<String>())
        private set

    var inventory by mutableStateOf(engine.player.inventory.toList())
        private set

    var exits by mutableStateOf(engine.currentRoom().exits.keys.toList())
        private set

    fun onCommand(input: String) {
        val command = CommandParser.parse(input)
        val result = engine.handleCommand(command)
        updateState(result)
    }

    private fun updateState(result: String) {
        roomDescription = engine.currentRoom().description
        inventory = engine.player.inventory.toList()
        exits = engine.currentRoom().exits.keys.toList()
        outputLog = outputLog + result
    }
}