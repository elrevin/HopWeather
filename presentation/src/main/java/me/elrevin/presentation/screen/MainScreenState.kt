package me.elrevin.presentation.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather

class MainScreenState {
    val errorCode: MutableState<String> = mutableStateOf("")

    val searchText: MutableState<String> = mutableStateOf("")
    val searchLocationsInProgress: MutableState<Boolean> = mutableStateOf(false)
    val locations: SnapshotStateList<Location> =
        mutableStateListOf()

    // Add in the pages list one page, for the Add New Tracked Location Screen
    val pages: SnapshotStateList<ScreenPage> =
        mutableStateListOf()
}

sealed class ScreenPage {
    data class LoadingPage(val location: Location, val weather: Weather?): ScreenPage()
    data class WeatherPage(val location: Location, val weather: Weather): ScreenPage()
    data class ErrorPage(val location: Location, val errorCode: String): ScreenPage()
}


