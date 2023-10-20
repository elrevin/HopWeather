package me.elrevin.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.elrevin.core.other.Constants
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.usecase.GetCurrentLocationUseCase
import me.elrevin.domain.usecase.GetLocationsUseCase
import me.elrevin.domain.usecase.GetWeatherUseCase
import me.elrevin.domain.usecase.RemoveLocationUseCase
import me.elrevin.domain.usecase.SaveLocationUseCase
import me.elrevin.domain.usecase.SearchLocationUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val searchLocationUseCase: SearchLocationUseCase,
    private val removeLocationUseCase: RemoveLocationUseCase
) : ViewModel() {
    val state = MainScreenState()

    private val obtainWeatherJobs = mutableListOf<Job>()
    private var currentLocation: Location? = null

    init {
        obtainLocations()
        searchLocation()
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.LocationAccessPermissionsGranted -> obtainCurrentLocation()
            is MainScreenEvent.SearchTextWasChanged -> onSearchTextWasChanged(event.text)
            is MainScreenEvent.ToggleLocation -> onToggleLocation(event.location)
        }
    }

    private fun onToggleLocation(location: Location) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (location.isTracked) {
                    removeLocationUseCase(location)
                } else {
                    saveLocationUseCase(location)
                }
                state.searchText.value = ""
                searchLocation()
            }
        }
    }

    private fun obtainLocations() {
        viewModelScope.launch {
            getLocationsUseCase().collect { result ->
                // Current location must be first

                val list = result.sortedBy {
                    if (currentLocation?.id == it.id) 0 else 1
                }

                // If we are already collecting weather data, stop to do it
                obtainWeatherJobs.forEach {
                    if (it.isActive) {
                        it.cancel()
                    }
                }

                obtainWeatherJobs.clear()

                // Create pages for the screen
                state.pages.clear()
                list.forEachIndexed { index, location ->
                    state.pages.add(index, ScreenPage.LoadingPage(location, null))
                    obtainWeather(index, location)
                }
            }
        }
    }

    private fun obtainCurrentLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val either = getCurrentLocationUseCase()
                if (either.isSuccess()) {
                    saveLocationUseCase(either.getValue())
                    currentLocation = either.getValue()
                } else if (either.isFailure()) {
                    error(either.getFailureMsgOrNull()!!)
                } else {
                    error(Constants.Errors.unknown)
                }
            }
        }
    }

    private fun obtainWeather(index: Int, location: Location) {
        val job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getWeatherUseCase(location).collect {
                    // put the weather data to pages list
                    state.pages[index] = when (it) {
                        is Either.Exception -> ScreenPage.ErrorPage(
                            location,
                            Constants.Errors.unknown
                        )
                        is Either.Failure -> ScreenPage.ErrorPage(
                            location,
                            it.getFailureMsgOrNull() ?: Constants.Errors.unknown
                        )
                        is Either.Loading -> ScreenPage.LoadingPage(location, it.getValueOrNull())
                        is Either.Success -> ScreenPage.WeatherPage(location, it.getValue())
                    }

                }
            }
        }
        obtainWeatherJobs.add(job)
    }

    private fun onSearchTextWasChanged(text: String) {
        state.searchText.value = text
        searchLocation()
    }

    var searchLocationJob: Job? = null

    private fun searchLocation() {
        if (searchLocationJob?.isActive == true) {
            searchLocationJob?.cancel()
        }

        searchLocationJob = viewModelScope.launch{
            searchLocationUseCase(state.searchText.value).collect{
                when {
                    it.isLoading() -> {
                        state.searchLocationsInProgress.value = true
                        val list = it.getValueOrNull() ?: listOf()
                        state.locations.clear()
                        state.locations.addAll(list)
                    }
                    it.isSuccess() -> {
                        state.searchLocationsInProgress.value = false
                        state.locations.clear()
                        state.locations.addAll(it.getValue())
                    }
                    it.isFailure() -> {
                        error(it.getFailureMsgOrNull() ?: Constants.Errors.unknown)
                    }
                }
            }
        }
    }

    private fun error(code: String) {
        state.errorCode.value = code
    }
}