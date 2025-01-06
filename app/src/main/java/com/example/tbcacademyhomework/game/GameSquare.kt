package com.example.tbcacademyhomework.game

import java.util.UUID

data class GameSquare(
    val id: UUID = UUID.randomUUID(),
    val value: Int? = null,
    val isActive: Boolean = false,
    val playerType: PlayerType? = null
)