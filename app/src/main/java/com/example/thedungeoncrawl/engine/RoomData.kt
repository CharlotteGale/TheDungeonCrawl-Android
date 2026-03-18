package com.example.thedungeoncrawl.engine

import com.example.thedungeoncrawl.model.Chest
import com.example.thedungeoncrawl.model.Item
import com.example.thedungeoncrawl.model.Lootable
import com.example.thedungeoncrawl.model.Room

val dungeonRooms: Map<String, Room> = mapOf(
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
        exits = mapOf("north" to "altar_room", "east" to "corridor", "south" to "exit", "west" to "mess_room"),
        items = mutableListOf(),
        chests = mutableListOf(
            Chest(
                id = "wooden_chest",
                name = "Wooden Chest",
                isLocked = false,
                isOpen = false,
                contents = mutableListOf(
                    Item(
                        id = "scroll",
                        name = "Scroll",
                        description = "A rolled scroll tied with a leather cord. " +
                                "The wax seal bears the same sigil as the altar to the north.",
                        readable = "The writing is hurried, scratched by someone who knew their time was short.\n\n" +
                                "— To move: use the compass on screen\n" +
                                "— To search a body: 'loot <name>'\n" +
                                "— To open containers: 'open <name>'\n" +
                                "— To inspect: 'examine <name>'\n" +
                                "— To take items: 'loot <name>' or 'take <item>'\n" +
                                "— To use something: 'use <item>'\n\n" +
                                "Whatever you do — don't touch the lever."
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
                                "One word is still legible: 'below'.",
                        readable = "Most pages are stuck together with damp. Only the last entry is legible.\n\n" +
                                "'We sealed the lower level three days ago. Malachar's orders. " +
                                "Harwick asked what was down there. Nobody answered him.\n\n" +
                                "Harwick didn't come to mess this morning.\n\n" +
                                "I don't think we sealed it to keep something in.\n\n" +
                                "I think we sealed it to keep us out.'"
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