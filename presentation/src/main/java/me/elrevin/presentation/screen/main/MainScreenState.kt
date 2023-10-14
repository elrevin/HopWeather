package me.elrevin.presentation.screen.main

import me.elrevin.domain.model.Location

data class MainScreenState(
    val errorCode: String = "",
    val locations: List<Location> = listOf()
)
