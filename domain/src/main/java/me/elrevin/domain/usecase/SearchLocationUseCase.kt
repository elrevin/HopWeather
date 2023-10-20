package me.elrevin.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository

class SearchLocationUseCase(
    private val repository: LocationRepository,
    private val loadLocationByNameUseCase: LoadLocationByNameUseCase
) {
    suspend operator fun invoke(locationName: String): Flow<Either<List<Location>>> = flow{
        val fromDb = repository.getLocationsByName(locationName)
        if (locationName.isNotEmpty()) {
            emit(Either.loading(fromDb))
            val result = loadLocationByNameUseCase(locationName)

            if (result.isSuccess()) {
                emit(Either.success(
                    fromDb + result.getValue().filter {
                        !fromDb.contains(it)
                    }
                ))
            } else {
                emit(
                    Either.fromEither(result)
                )
            }
        } else {
            emit(Either.success(fromDb))
        }
    }.flowOn(Dispatchers.IO)
}