package me.elrevin.presentation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import me.elrevin.presentation.screen.GetPermissions
import me.elrevin.presentation.screen.MainScreen
import me.elrevin.presentation.screen.MainScreenEvent
import me.elrevin.presentation.screen.MainScreenViewModel

@Composable
fun App() {
    val vm: MainScreenViewModel = viewModel()
    val pages = vm.state.pages
    val searchText by vm.state.searchText
    val locations = vm.state.locations
    val searchLocationInProgress by vm.state.searchLocationsInProgress
    MainScreen(
        pages = pages,
        searchText = searchText,
        locationList = locations,
        searchLocationInProgress = searchLocationInProgress,
        getPermissions = {
            GetPermissions {
                vm.onEvent(MainScreenEvent.LocationAccessPermissionsGranted)
            }
        },
        onChangeSearchText = {
            vm.onEvent(
                MainScreenEvent.SearchTextWasChanged(it)
            )
        },
        onToggleLocation = {
            vm.onEvent(
                MainScreenEvent.ToggleLocation(it)
            )
        }
    )
}