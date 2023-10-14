package me.elrevin.presentation.screen.main

sealed class MainScreenEvent {
    object LocationAccessPermissionsGranted: MainScreenEvent()
}