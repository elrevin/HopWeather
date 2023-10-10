package me.elrevin.domain.repository

import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location

interface LocationRepository {
    suspend fun searchLocation(locationName: String): Either<List<Location>>
    suspend fun saveLocation(location: Location)
}