package me.elrevin.domain.usecase

import me.elrevin.domain.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke() = repository.getCurrentLocation()
}