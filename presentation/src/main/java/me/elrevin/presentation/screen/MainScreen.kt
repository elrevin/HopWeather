package me.elrevin.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.elrevin.domain.model.Location
import me.elrevin.presentation.screen.add_new_location_page.AddNewLocationPage
import me.elrevin.presentation.screen.error_page.ErrorPage
import me.elrevin.presentation.screen.loading.LoadingPage
import me.elrevin.presentation.screen.weather_page.WeatherPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    pages: List<ScreenPage>,
    searchText: String,
    locationList: List<Location>,
    searchLocationInProgress: Boolean,
    getPermissions: @Composable () -> Unit,
    onChangeSearchText: (String) -> Unit,
    onToggleLocation: (Location) -> Unit
) {
    getPermissions()

    val pagerState = rememberPagerState(pageCount = {
        pages.size + 1
    })

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (it == pages.size) {
                AddNewLocationPage(
                    searchText = searchText,
                    locationList = locationList,
                    searchLocationInProgress = searchLocationInProgress,
                    onChangeSearchText = onChangeSearchText,
                    onToggleLocation = onToggleLocation
                )
            } else {
                MainScreenPage(page = pages[it])
            }
        }
    }
}

@Composable
fun MainScreenPage(
    page: ScreenPage
) {
    when {
        page is ScreenPage.ErrorPage -> ErrorPage(location = page.location, page.errorCode)
        page is ScreenPage.LoadingPage && page.weather == null -> LoadingPage(location = page.location)
        page is ScreenPage.LoadingPage && page.weather != null -> WeatherPage(
            location = page.location,
            weather = page.weather,
            true
        )

        page is ScreenPage.WeatherPage ->
            WeatherPage(location = page.location, weather = page.weather, false)
    }
}

