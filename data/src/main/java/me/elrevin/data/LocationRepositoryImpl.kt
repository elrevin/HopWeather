package me.elrevin.data

import me.elrevin.data.local.Dao
import me.elrevin.data.mapper.toDataEntity
import me.elrevin.data.mapper.toDomainModel
import me.elrevin.data.remote.LocationRemoteSource
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val dao: Dao,
    private val remoteSource: LocationRemoteSource
): LocationRepository {
    override suspend fun searchLocation(locationName: String): Either<List<Location>> {
        val result = remoteSource.searchLocation(locationName)
        if (result.isSuccess()) {
            return Either.success(result.getValue().map { it.toDomainModel() })
        }
        return Either.fromEither(result)
    }

    override suspend fun saveLocation(location: Location) {
        dao.upsertLocation(location.toDataEntity())
    }

}