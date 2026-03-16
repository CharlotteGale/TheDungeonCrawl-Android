# The Dungeon Crawl

> *The entrance looms before you. Torchlight flickers somewhere within. You probably shouldn't go in.*
>
> *You go in anyway.*

A text-based dungeon crawl built in Kotlin for Android. Navigate a crumbling dungeon, interact with your environment, manage your inventory, and try not to think too hard about what's been eating those apples.

---

## Getting Started

### Prerequisites
- Android Studio Panda or later
- Android SDK API 26+
- A device or emulator running Android 8.0+

### Running the game
Open the project in Android Studio and hit **Run** — the dungeon awaits.

---

## How to Play

The game is driven by text commands and on-screen controls. Read the room description, decide what to do, type it in or tap a direction.

### Navigation

| Command | Description |
|---|---|
| `go <direction>` | Move in a direction |
| Direction pad | Tap N/S/E/W — disabled if no exit available |

Directions: `north`, `south`, `east`, `west`

### Inventory

| Command | Description |
|---|---|
| `take <item>` | Pick up an item from the room |
| `drop <item>` | Drop an item from your inventory |
| `inventory` / `inv` / `i` | List what you're carrying |

### Interaction

| Command | Description |
|---|---|
| `open <target>` | Open a door or chest |
| `examine <target>` / `x <target>` | Inspect an item closely |
| `loot <target>` | Take an item or everything from an open chest |
| `use <item>` | Use an item from your inventory |

### General

| Command | Description |
|---|---|
| `help` / `h` / `?` | Show available commands |
| `quit` / `exit` / `q` | Leave the dungeon (coward) |

---

## The Dungeon

A rough map of what you can currently explore:

```
                          [Altar Room]
                               |
                             north
                               |
[Mess Room] — west — [Entrance Chamber] — east — [Eastern Corridor] — [Barracks]
                               |
                             south
                               |
                         [Sunlight/Exit]

[Supply Store] — north — [Mess Room]
```

*Some doors are jammed. Some chests are locked. Some things are better left alone.*

---

## Roadmap

The dungeon is far from finished. Planned features in rough order:

- **NPC encounters** — not everything down here is dead
- **Combat system** — some things are dead but won't stay that way
- **Traps** — that lever in the corridor does *something*
- **ASCII art map** — rendered alongside the direction pad
- **Deeper dungeon** — whatever is in the dark beyond the mess room
- **Sound & atmosphere** — ambient audio to match the mood

---

## Built With

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Android Studio Panda
- A probably unwise amount of atmospheric description

---

## Console Version

The original console version of The Dungeon Crawl is available as a separate project — the Android port is built on the same core game engine.