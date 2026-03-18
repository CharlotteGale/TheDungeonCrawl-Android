package com.example.thedungeoncrawl.model

data class Item(
    val id: String,
    val name: String,
    val description: String,
    val readable: String? = null
)