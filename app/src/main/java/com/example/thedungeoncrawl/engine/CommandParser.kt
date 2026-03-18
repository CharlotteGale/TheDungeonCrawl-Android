package com.example.thedungeoncrawl.engine

object CommandParser {
    fun parse(input: String): Command {
        val parts = input.trim().lowercase().split("\\s+".toRegex())
        val keyword = parts[0]
        val argument = parts.drop(1).joinToString(" ")

        return when (keyword) {
            "go", "move", "walk" -> Command.Go(argument)
            "inventory", "inv", "i" -> Command.Inventory
            "take", "pick" -> Command.Take(argument)
            "drop" -> Command.Drop(argument)
            "open", "pry", "try" -> Command.Open(argument)
            "loot", "grab" -> Command.Loot(argument)
            "examine", "ex", "inspect", "x" -> Command.Examine(argument)
            "use" -> Command.Use(argument)
            "read" -> Command.Read(argument)
            "help", "h", "?" -> Command.Help
            "quit", "exit", "q" -> Command.Quit
            else -> Command.Unknown
        }
    }
}