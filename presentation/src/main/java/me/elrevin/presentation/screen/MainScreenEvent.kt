package me.elrevin.presentation.screen

import me.elrevin.domain.model.Location

sealed class MainScreenEvent {
    object LocationAccessPermissionsGranted: MainScreenEvent()
    class SearchTextWasChanged(val text: String): MainScreenEvent()
    class ToggleLocation(val location: Location): MainScreenEvent()
}