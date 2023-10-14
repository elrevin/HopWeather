package me.elrevin.presentation.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.elrevin.core.other.Constants
import me.elrevin.domain.model.Location
import me.elrevin.domain.usecase.GetCurrentLocationUseCase
import me.elrevin.domain.usecase.GetLocationsUseCase
import me.elrevin.domain.usecase.SaveLocationUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
): ViewModel() {
    private val mutableState = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = mutableState

    init {
        obtainLocations()
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.LocationAccessPermissionsGranted -> obtainCurrentLocation()
        }
    }

    private fun obtainLocations() {
        viewModelScope.launch {
            getLocationsUseCase().collect{
                mutableState.value = mutableState.value.copy(locations = it)
            }
        }
    }

    private fun obtainCurrentLocation() {
        viewModelScope.launch {
            val either = getCurrentLocationUseCase()
            if (either.isSuccess()) {
                saveLocationUseCase(either.getValue())
                saveLocationUseCase(Location(
                    id = "berlin-berlin-germany",
                    name = "Berlin",
                    country = "Germany",
                    region = "Berlin"
                ))
            } else if (either.isFailure()) {
                error(either.getFailureMsgOrNull()!!)
            } else {
                error(Constants.Errors.unknown)
            }
        }
    }

    private fun error(code: String) {
        mutableState.value = mutableState.value.copy(errorCode = code)
    }

    private fun resetError() {
        mutableState.value = mutableState.value.copy(errorCode = "")
    }
}