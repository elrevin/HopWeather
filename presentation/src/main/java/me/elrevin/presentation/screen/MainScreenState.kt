package me.elrevin.presentation.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather

data class MainScreenState (
    val errorCode: MutableState<String> = mutableStateOf(""),

    // Add in the pages list one page, for the Add New Tracked Location Screen
    val pages: SnapshotStateList<PageState> =
        mutableStateListOf(PageState.AddNewLocationPage)
)

sealed class PageState {
    object AddNewLocationPage: PageState()
    data class EmptyPage(val location: Location): PageState()
    data class LoadingPage(val location: Location): PageState()
    data class WeatherPage(val location: Location, val weather: Weather): PageState()
    data class ErrorPage(val location: Location, val errorCode: String): PageState()
}
