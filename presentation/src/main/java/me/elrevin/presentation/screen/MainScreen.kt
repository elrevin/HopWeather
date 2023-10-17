package me.elrevin.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.elrevin.presentation.screen.add_new_location_page.AddNewLocationPage
import me.elrevin.presentation.screen.empty_page.EmptyPage
import me.elrevin.presentation.screen.error_page.ErrorPage
import me.elrevin.presentation.screen.loading.LoadingPage
import me.elrevin.presentation.screen.weather_page.WeatherPage

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
        state.pages.size
    })

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MainScreenPage(page = state.pages[it])
        }
    }
}

@Composable
fun MainScreenPage(page: PageState) {
    when(page) {
        PageState.AddNewLocationPage -> AddNewLocationPage()
        is PageState.EmptyPage -> EmptyPage(location = page.location)
        is PageState.ErrorPage -> ErrorPage(location = page.location, page.errorCode)
        is PageState.LoadingPage -> LoadingPage(location = page.location)
        is PageState.WeatherPage -> WeatherPage(location = page.location, weather = page.weather)
    }
}

