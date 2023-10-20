package me.elrevin.domain.usecase

import kotlinx.coroutines.flow.distinctUntilChangedBy
import me.elrevin.domain.repository.LocationRepository

class GetLocationsUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke() = repository.getLocations()
        .distinctUntilChangedBy {
            it.joinToString {
                it.id + ":"
            }
        }
}