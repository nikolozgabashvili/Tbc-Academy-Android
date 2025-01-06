package com.example.tbcacademyhomework.config

import java.util.UUID

data class ConfigurationItem(
    val id: UUID = UUID.randomUUID(),
    val value: Int,
    val selected: Boolean = false
)