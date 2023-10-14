package me.elrevin.presentation.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    getPermissions: @Composable (() -> Unit) ->Unit,
    onEvent: (MainScreenEvent) -> Unit
) {
    getPermissions {
        onEvent(MainScreenEvent.LocationAccessPermissionsGranted)
    }

    val pagerState = rememberPagerState(pageCount = {
        state.locations.size
    })

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(412.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Green), contentAlignment = Alignment.Center) {
                Text(text = state.locations[it].name)
            }
        }
    }
}