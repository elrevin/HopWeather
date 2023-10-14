package me.elrevin.domain.usecase

import me.elrevin.domain.repository.LocationRepository

class GetLocationsUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke() = repository.getLocations()
}