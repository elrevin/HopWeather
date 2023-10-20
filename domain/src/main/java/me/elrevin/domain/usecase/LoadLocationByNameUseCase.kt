package me.elrevin.domain.usecase

import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository

class LoadLocationByNameUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(locationName: String): Either<List<Location>> =
        repository.loadLocations(locationName)
}