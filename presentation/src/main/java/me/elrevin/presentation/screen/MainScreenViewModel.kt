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
import me.elrevin.domain.usecase.SaveLocationUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    val state = MainScreenState()

    private val obtainWeatherJobs = mutableListOf<Job>()
    private var currentLocation: Location? = null

    init {
        obtainLocations()
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.LocationAccessPermissionsGranted -> obtainCurrentLocation()
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
                list.forEachIndexed { index, location ->

                    // If page with this index is existing, replace it by new, for more smooth
                    // change of interface
                    if (state.pages.size - 1 > index) {
                        state.pages[index] = PageState.EmptyPage(location)
                    } else {
                        // Pages list does not contain a page with this index
                        // it will be added
                        state.pages.add(index, PageState.EmptyPage(location))
                    }
                    obtainWeather(index, location)
                }

                // If list of obtained locations has fewer items then pages list (except last page for
                // add location to tracking), we need to remove all unnecessary pages
                if (list.size < state.pages.size - 1) {
                    state.pages.removeRange(list.size, state.pages.size - 2)
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
                        is Either.Exception -> PageState.ErrorPage(
                            location,
                            Constants.Errors.unknown
                        )
                        is Either.Failure -> PageState.ErrorPage(
                            location,
                            it.getFailureMsgOrNull() ?: Constants.Errors.unknown
                        )
                        is Either.Loading -> PageState.LoadingPage(location)
                        is Either.Success -> PageState.WeatherPage(location, it.getValue())
                    }

                }
            }
        }
        obtainWeatherJobs.add(job)
    }


    private fun error(code: String) {
        state.errorCode.value = code
    }

    private fun resetError() {
        state.errorCode.value = ""
    }
}