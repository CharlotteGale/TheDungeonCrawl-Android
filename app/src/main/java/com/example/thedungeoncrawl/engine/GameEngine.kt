package com.example.thedungeoncrawl.engine

import com.example.thedungeoncrawl.model.Chest
import com.example.thedungeoncrawl.model.Item
import com.example.thedungeoncrawl.model.Lootable
import com.example.thedungeoncrawl.model.Player
import com.example.thedungeoncrawl.model.Room

class GameEngine {
    private val rooms: Map<String, Room> = mapOf(
        "entrance" to Room(
            id = "entrance",
            description = "You stand in a dimly lit stone chamber, the air stale and cold against your skin. " +
                    "\nTorch sconces line the walls, their flames long since guttered — all but one, which flickers weakly in a draft you cannot place. " +
                    "\nTo the north, a crumbling archway opens into a vaulted chamber beyond. " +
                    "\nTo the east, a narrow corridor disappears into shadow, the air from it heavy and damp. " +
                    "\nTo the west, a heavy wooden door sits firmly in its frame — the hinges are rusted solid, it isn't going anywhere without help. " +
                    "\nSlumped against the western wall is a skeleton, its bony fingers still curled around nothing. Whatever it was carrying is long gone. " +
                    "\nBeside it sits a wooden chest, its lid closed but unlocked. " +
                    "\nBehind you to the south, a sliver of daylight cuts through the gloom — the way out.",
            exits = mapOf("north" to "altar_room", "east" to "corridor", "south" to "exit"),
            items = mutableListOf(),
            chests = mutableListOf(
                Chest(
                    id = "wooden_chest",
                    name = "Ornate Chest",
                    isLocked = false,
                    isOpen = false,
                    contents = mutableListOf(
                        Item(
                            id = "scroll",
                            name = "Scroll",
                            description = "A rolled scroll tied with a leather cord. " +
                                    "The wax seal bears the same sigil as the altar to the north."
                        )
                    )
                )
            ),
            lootables = mutableListOf(
                Lootable(
                    id = "skeleton",
                    name = "Skeleton",
                    description = "A long dead soldier, slumped against the wall. " +
                            "Whatever killed it, it didn't have time to reach for its weapon. " +
                            "A tarnished key hangs from its belt.",
                    items = mutableListOf(
                        Item(
                            id = "chest_2_key",
                            name = "Tarnished Key",
                            description = "A tarnished key, still attached to a leather fob bearing a faded insignia."
                        )
                    )
                )
            )
        ),
        "altar_room" to Room(
            id = "altar_room",
            description = "The chamber opens into a vaulted space, the ceiling lost in shadow above. " +
                    "\nAt the far end stands a broad stone altar, its surface etched with the sigil of Malachar, the God of War and Conquest. " +
                    "\nTwo guttering candles flank the altar, their weak flames barely holding back the dark. " +
                    "\nAncient weapons line the walls, rusted and ceremonial. A warrior's helm sits at the centre of the altar, dull and cracked. " +
                    "\nTo the south, the crumbling archway leads back to the entrance chamber. " +
                    "\nIn the alcove beside it, a torch sits unlit in its iron bracket.",
            exits = mapOf("south" to "entrance"),
            items = mutableListOf(
                Item(id = "unlit_torch", name = "Torch", description = "A torch, cold and unlit. It looks like it would burn well enough."),
                Item(id = "chest_1_key", name = "Small Key", description = "A small iron key, worn smooth with age.")
            )
        ),
        "corridor" to Room(
            id = "corridor",
            description = "A narrow stone corridor stretches ahead, torches burning steadily in their sconces at regular intervals. " +
                    "\nSomeone, or something, has kept them lit. " +
                    "\nThe air is stale but the flames don't flicker, which is somehow worse. " +
                    "\nThe passage feels longer than it should, the far end swallowed in a deceptive stillness. " +
                    "\nAs you walk, you notice a flagstone that sits fractionally higher than the rest. You place your foot carefully. The flagstone shifts slightly underfoot, and you freeze. " +
                    "\nIt settles. You breathe again.  " +
                    "\nThe walls either side are not entirely solid; narrow slits run at waist height, dark and hollow, angled inward. " +
                    "\nThey are dark and silent, but your imagination fills them with things you'd rather not think about. " +
                    "\nMidway along the corridor a rusted iron lever protrudes from the wall, worn smooth at the handle as though it has been gripped many times before. " +
                    "\nTo the west, the entrance chamber. Ahead to the east, you can see three doors.",
            exits = mapOf("west" to "entrance", "east" to "barracks"),
            items = mutableListOf()
        ),
        "barracks" to Room(
            id = "barracks",
            description = "Of the three doors at the corridor's end, only one swings open reluctantly, with a groan of rusted hinges.. " +
                    "\nFour soldier's cots line the walls, their blankets thrown back as though the occupants left in haste. " +
                    "\nYet a thick layer of dust coats every surface, undisturbed for what must be years. " +
                    "\nAt the foot of each cot sits a chest. Most hang open and empty, their lids thrown back and forgotten. " +
                    "\nTwo remain shut, their heavy iron clasps fastened tight; the lids don't budge when you test them. " +
                    "\nScattered across the floor lie the remnants of a soldier's life: a cracked leather boot, a dented tin cup, a torn piece of cloth bearing a faded insignia. " +
                    "\nA short sword leans against the far wall, its blade dulled but intact. " +
                    "\nTo the west, the corridor winds back toward the entrance.",
            exits = mapOf("west" to "corridor"),
            items = mutableListOf(
                Item(id = "short_sword", name = "Short Sword", description = "A short sword, its blade dulled but intact.")
            ),
            chests = mutableListOf(
                Chest(
                    id = "chest_1",
                    name = "Iron Chest",
                    isLocked = true,
                    contents = mutableListOf(
                        Item(
                            id = "mess_room_key",
                            name = "Rusted Key",
                            description = "A heavy rusted key. It looks like it would fit a large door."
                        )
                    )
                ),
                Chest(
                    id = "chest_2",
                    name = "Soldiers Chest",
                    isLocked = true,
                    contents = mutableListOf(
                        Item(
                            id = "gold_coins",
                            name = "Gold Coins",
                            description = "A small pouch of gold coins, still heavy. Someone left in a hurry."
                        ),
                        Item(
                            id = "journal",
                            name = "Soldiers Journal",
                            description = "A leather bound journal, its pages yellowed and brittle. " +
                                    "The last entry is dated, but the ink has run. " +
                                    "One word is still legible: 'below'."
                        )
                    )
                )
            )
        ),
        "mess_room" to Room(
            id = "mess_room",
            description = "You squeeze through the jammed door into what was once the dungeon's mess room. " +
                    "\nLong wooden tables run the length of the room, flanked by benches worn smooth from years of use. " +
                    "\nA stone hearth dominates the far wall, cold and long dead, though a faint smell of woodsmoke clings to the air as though it hasn't quite given up. " +
                    "\nIron candle holders are fixed to the walls at regular intervals, and unlike the rest of the dungeon, most still hold candles — enough that the room feels almost habitable. Almost. " +
                    "\nPinned to the wall beside the hearth is a rough map of the dungeon. The edges have been gnawed away — mice, you tell yourself. " +
                    "\nThe remaining portion shows the entrance chamber, this room, the altar room and the eastern corridor. Everything beyond is gone. " +
                    "\nYou look at the floor. There are no mouse droppings. " +
                    "\nTo the south, a door leads to what looks like a store room. " +
                    "\nTo the east, the jammed door back to the entrance chamber.",
            exits = mapOf("south" to "supply_store", "east" to "entrance"),
            items = mutableListOf()
        ),
        "supply_store" to Room(
            id = "supply_store",
            description = "The door creaks open into a low-ceilinged store room, the light from the mess room behind you barely reaching the back wall. " +
                    "\nMost of the shelving has been pulled down or collapsed, the contents long since scattered or taken. " +
                    "\nIn the dim light you can make out a few shelves still standing along the right wall. " +
                    "\nMost are bare, but a few hold what look like sealed clay pots and folded sacking — the kind of supplies that outlast everything else. " +
                    "\nOn the shelf nearest you, catching what little light there is, sit two apples. They look almost fresh. " +
                    "\nYou look at them for a long moment. " +
                    "\nThe dust on the floor is thick except for one spot near the back wall, where a single large footprint breaks the surface. " +
                    "\nYou can't make out what made it in the dark. " +
                    "\nTo the north, the mess room.",
            exits = mapOf("north" to "mess_room"),
            items = mutableListOf(
                Item(id = "apple", name = "Apple", description = "Two apples, fresher than they have any right to be.")
            )
        ),
        "exit" to Room(
            id = "exit",
            description = "Warm sunlight spills over you as you step outside. " +
                    "\nThe dungeon entrance looms behind you to the north. " +
                    "\nYou could leave... but something draws you back in.",
            exits = mapOf("north" to "entrance"),
            items = mutableListOf()
        )
    )

    val player = Player(currentRoomId = "entrance", inventory = mutableListOf())

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
            is Command.Inventory -> handleInventory()
            is Command.Unknown -> "You mutter to yourself. Nothing happens. \nType 'help' for a list of commands."
        }
    }

    fun handleGo(direction: String): String {
         if (currentRoom().id == "entrance" && direction == "west") {
            return if (player.hasItem("mess_room_key")) {
                player.dropItem("mess_room_key")
                player.currentRoomId = "mess_room"
                currentRoom().description
            } else {
                "You throw your shoulder against the door. The rusted hinges groan but don't give. It isn't going anywhere without help."
            }
        }

        val exit = currentRoom().exits[direction]
        return if (exit != null) {
            player.currentRoomId = exit
            "You head in $direction"
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
                    lootable.description
                    "You find:\n" + lootable.items.joinToString("\n") { " - ${it.name}" }
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