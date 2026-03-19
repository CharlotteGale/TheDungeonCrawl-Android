package com.example.thedungeoncrawl.engine

import com.example.thedungeoncrawl.model.Item
import com.example.thedungeoncrawl.model.Player
import com.example.thedungeoncrawl.model.Room
import com.example.thedungeoncrawl.model.RoomMarkers

class GameEngine {
    private val rooms: Map<String, Room> = dungeonRooms
    private val visitedRooms = mutableSetOf("entrance")

    val player = Player(currentRoomId = "entrance", inventory = mutableListOf(Item(
        id = "dungeon_map",
        name = "Dungeon Map",
        description = "A rough map of the dungeon, its edges gnawed away."
    )))

    fun handleCommand(command: Command): String {
        return when (command) {
            is Command.Go -> handleGo(command.direction)
            is Command.Help -> printHelp()
            is Command.Quit -> "You flee the dungeon... coward."
            is Command.Open -> handleOpen(command.target)
            is Command.Take -> handleTake(command.itemName)
            is Command.Drop -> handleDrop(command.itemName)
            is Command.Loot -> handleLoot(command.target)
            is Command.Examine -> handleExamine(command.target)
            is Command.Use -> handleUse(command.itemName)
            is Command.Read -> handleRead(command.itemName)
            is Command.Inventory -> handleInventory()
            is Command.Unknown -> "You mutter to yourself. Nothing happens. \nExpand the 'Help' section above or click the '?' for a list of commands."
        }
    }

    fun handleGo(direction: String): String {
         if (currentRoom().id == "entrance" && direction == "west") {
            return if (player.hasItem("mess_room_key")) {
                "The door is locked. You'll need to use the key."
            } else {
                "You throw your shoulder against the door. The rusted hinges groan but don't give. It isn't going anywhere without help."
            }
        }

        val exit = currentRoom().exits[direction]
        return if (exit != null) {
            player.currentRoomId = exit
            visitedRooms.add(player.currentRoomId)
            "You head $direction"
        } else {
            "You can't go that way."
        }
    }

    fun handleTake(itemName: String): String {
        if (itemName.isEmpty()) {
            return "Take what, exactly?"
        }

        val room = currentRoom()
        val item = room.items.find { it.name.lowercase().contains(itemName.lowercase()) }

        return if (item != null) {
            room.items.remove(item)
            player.takeItem(item)
            "You pick up the ${item.name}."
        } else {
            "There's nothing to take."
        }
    }

    fun handleDrop(itemName: String): String {
        val item = player.inventory.find { it.name.lowercase().contains(itemName.lowercase()) }

        if (itemName.isEmpty()) {
            return "Drop what?"
        }

        return if (item != null) {
            player.dropItem(item.id)
            currentRoom().items.add(item)
            "You drop the ${item.name}."
        } else {
            "You've not got a $itemName."
        }
    }

    fun handleInventory(): String {
        return if(player.inventory.isEmpty()) {
            "You aren't carrying anything."
        } else {
            "You are carrying:\n" + player.inventory.joinToString("\n") { " - ${it.name}" }
        }
    }

    fun handleOpen(target: String): String {
        when {
            target == "door" -> {
                return if (currentRoom().id == "entrance") {
                    "Try heading west."
                } else if (currentRoom().exits.containsValue("mess_room") ||
                    currentRoom().exits.containsValue("entrance")) {
                    "The door is jammed solid in its frame."
                } else {
                    "There's no door to open here."
                }
            }
            target.isEmpty() -> return "Open what, exactly?"
            else -> {
                val chest = currentRoom().chests.find {
                    it.name.lowercase().contains(target.lowercase())
                }
                return when {
                    chest == null -> "There's no chest called '$target' here."
                    chest.isOpen -> "The ${chest.name} is already open."
                    chest.isLocked -> "The iron clasps on the ${chest.name} are fastened tight. The lid doesn't budge."
                    else -> {
                        chest.isOpen = true
                        if (chest.contents.isEmpty()) {
                            "You open the ${chest.name}. It's empty."
                        } else {
                            "You lift the lid of the ${chest.name}. \nType 'examine ${chest.name.lowercase()}' to inspect the contents."
                        }
                    }
                }
            }
        }
    }

    fun handleExamine(target: String): String {
        if (target.isEmpty()) {
            return "Examine what exactly?"
        }

        // Check lootables by name
        val lootable = currentRoom().lootables.find { it.name.lowercase().contains(target.lowercase()) }
        if (lootable != null ) {
            return if (lootable.isLooted)
                "${lootable.description}\n\nYou've already taken everything."
            else
                lootable.description
        }

        // Check chests by name
        val chest = currentRoom().chests.find { it.name.lowercase().contains(target.lowercase()) }
        if (chest != null) {
            return when {
                !chest.isOpen -> "The ${chest.name} is closed. Try opening it first."
                chest.contents.isEmpty() -> "The ${chest.name} is empty."
                else -> {
                    chest.isExamined = true
                    "Inside the ${chest.name}, you find:\n" + chest.contents.joinToString("\n") { " - ${it.name}" }
                }
            }
        }

        // Check room items and inventory by name
        val roomItem = currentRoom().items.find { it.name.lowercase().contains(target.lowercase()) }
        val inventoryItem = player.inventory.find { it.name.lowercase().contains(target.lowercase()) }
        return when {
            roomItem != null -> roomItem.description
            inventoryItem != null -> inventoryItem.description
            else -> "You can't examine that."
        }
    }

    fun handleLoot(target: String): String {
        if (target.isEmpty()) {
            return "Loot what, exactly?"
        }

        // Check lootables by name
        val lootable = currentRoom().lootables.find { it.name.lowercase().contains(target.lowercase()) }
        if (lootable != null) {
            return when {
                lootable.isLooted -> "\nThere's nothing left on the ${lootable.name}."
                lootable.items.isEmpty() -> {
                    lootable.isLooted = true
                    "You search the ${lootable.name} but find nothing of use."
                }
                else -> {
                    lootable.isLooted = true
                    val itemList = lootable.items.joinToString("\n") { " - ${it.name}" }
                    lootable.items.forEach { currentRoom().items.add(it) }
                    lootable.items.clear()
                    "${lootable.description}\n\nYou find:\n$itemList\n\nUse 'take <item>' to pick something up."
                }
            }
        }

        // Check chests by name
        val chest = currentRoom().chests.find { it.name.lowercase().contains(target.lowercase()) }
        if (chest != null) {
            return when {
                !chest.isOpen -> "The ${chest.name} is closed. Try opening it first."
                chest.contents.isEmpty() -> "\nThe ${chest.name} is empty."
                !chest.isExamined -> "\nYou haven't examined the contents yet. Try 'examine ${chest.name.lowercase()}' first."
                else -> {
                    // Check if target is a specific item rather than the chest itself
                    val chestItem = chest.contents.find { it.name.lowercase().contains(target.lowercase()) }
                    if (chestItem != null) {
                        chest.contents.remove(chestItem)
                        player.takeItem(chestItem)
                        "\nYou take the ${chestItem.name}."
                    } else {
                        val taken = chest.contents.toList()
                        taken.forEach { player.takeItem(it) }
                        chest.contents.clear()
                        "\nYou take everything from the ${chest.name}!"
                    }
                }
            }
        }

        return "There's nothing to loot here."
    }

    fun handleUse(itemName: String): String {
        val item = player.inventory.find {
            it.name.lowercase().contains(itemName.lowercase()) ||
                    it.id.lowercase().contains(itemName.lowercase())
        }

        if (item == null) {
            return "You don't have a $itemName"
        }

        return when {
            item.id == "unlit_torch" -> {
                player.dropItem("unlit_torch")
                player.takeItem(Item(
                    id = "lit_torch",
                    name = "Lit Torch",
                    description = "A torch, burning steadily. It pushes the shadows back."
                ))
                "You strike a light. The torch catches, pushing the shadows back."
            }
            item.id == "lit_torch" -> {
                "The torch is already lit."
            }
            item.id == "mess_room_key" -> {
                if (currentRoom().id == "entrance") {
                    player.dropItem("mess_room_key")
                    player.currentRoomId = "mess_room"
                    visitedRooms.add("mess_room")
                    "The key grinds in the lock. The door opens with a groan of rusty hinges, but jams on the flagstone. You can just fit through."
                } else {
                    "There's no lock here for this key."
                }
            }
            item.id.endsWith("_key") -> {
                val targetId = item.id.removeSuffix("_key")

                // Check chests first
                val chest = currentRoom().chests.find { it.id == targetId }
                if (chest != null) {
                    return if (!chest.isLocked) {
                        "The chest is already unlocked."
                    } else {
                        chest.isLocked = false
                        player.dropItem(item.id)
                        "The key turns smoothly. The chest unlocks with a heavy clunk."
                    }
                }

                "You can't use that key here."
            }
            else -> "You're not sure how to use that."
        }
    }

    fun handleRead(itemName: String): String {
        if (itemName.isEmpty()) return "Read what, exactly?"

        val item = player.inventory.find {
            it.name.lowercase().contains(itemName.lowercase()) ||
                    it.id.lowercase().contains(itemName.lowercase())
        }

        if (item == null) return "You aren't carrying anything called '$itemName'."

        return item.readable
            ?: "There's nothing to read about ${item.name}."
    }

    fun getRoomDescription(): String {
        val room = currentRoom()
        val base = room.description

        return when (room.id) {
            "altar_room" -> if (player.hasItem("lit_torch") && !player.hasItem("chest_1_key"))
                "$base\n\nThe torchlight catches something in the shadows." +
                        "\nA small key glints on the stone floor beside the altar."
            else base
            "barracks" -> {
                val chestInfo = room.chests.joinToString("\n") { chest ->
                    val status = when {
                        chest.isLocked -> "locked"
                        chest.isOpen -> "open"
                        else -> "unlocked"
                    }
                    "${chest.name} ($status)"
                }
                "$base\n\n$chestInfo"
            }
            else -> base
        }
    }

    fun getVisitedRooms(): Set<String> = visitedRooms.toSet()

    fun getRoomMarkers(): Map<String, RoomMarkers> {
        return rooms.mapValues { (_, room) ->
            RoomMarkers(
                hasChest = room.chests.isNotEmpty(),
                hasLootable = room.lootables.any { !it.isLooted },
                hasItems = room.items.isNotEmpty()
            )
        }
    }

    fun printHelp(): String {
        return "Commands:\n" +
                "  go <direction>  — move in a direction\n" +
                "  inventory       — list what you're carrying\n" +
                "  take <item>     — pick up an item\n" +
                "  drop <item>     — drop an item\n" +
                "  open <target>   — open a chest or door\n" +
                "  examine <target>— inspect something closely\n" +
                "  loot <target>   — loot a chest or body\n" +
                "  use <item>      — use an item\n" +
                "  help            — show this list\n" +
                "  quit            — exit the game"
    }

    fun currentRoom(): Room {
        return rooms[player.currentRoomId]
            ?: throw IllegalStateException("Room '${player.currentRoomId}' does not exist.")
    }
}