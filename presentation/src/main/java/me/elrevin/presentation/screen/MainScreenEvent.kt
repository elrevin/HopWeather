package me.elrevin.presentation.screen

sealed class MainScreenEvent {
    object LocationAccessPermissionsGranted: MainScreenEvent()
}