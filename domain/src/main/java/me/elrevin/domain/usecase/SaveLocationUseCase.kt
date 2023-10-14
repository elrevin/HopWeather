package me.elrevin.domain.usecase

import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository

class SaveLocationUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(location: Location) {
        repository.saveLocation(location)
    }
}