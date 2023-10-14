package me.elrevin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.elrevin.presentation.screen.main.GetPermissions
import me.elrevin.presentation.screen.main.MainScreen
import me.elrevin.presentation.screen.main.MainScreenEvent
import me.elrevin.presentation.screen.main.MainScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routing.mainScreen
    ) {
        composable(
            route = Routing.mainScreen
        ) {
            val vm: MainScreenViewModel = hiltViewModel()

            MainScreen(
                state = vm.state.value,
                getPermissions = {
                    GetPermissions {
                        vm.onEvent(MainScreenEvent.LocationAccessPermissionsGranted)
                    }
                },
                onEvent = {}
            )
        }
    }
}