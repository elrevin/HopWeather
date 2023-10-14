package me.elrevin.domain.repository

import kotlinx.coroutines.flow.Flow
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location

interface LocationRepository {
    fun getLocations(): Flow<List<Location>>
    suspend fun searchLocation(locationName: String): Either<List<Location>>
    suspend fun saveLocation(location: Location)
    suspend fun getCurrentLocation(): Either<Location>
}