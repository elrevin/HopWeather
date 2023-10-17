package me.elrevin.presentation.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import me.elrevin.presentation.screen.GetPermissions
import me.elrevin.presentation.screen.MainScreen
import me.elrevin.presentation.screen.MainScreenEvent
import me.elrevin.presentation.screen.MainScreenViewModel

@Composable
fun App() {
    val vm: MainScreenViewModel = viewModel()
    MainScreen(
        state = vm.state,
        getPermissions = {
            GetPermissions {
                vm.onEvent(MainScreenEvent.LocationAccessPermissionsGranted)
            }
        },
        onEvent = {}
    )
}