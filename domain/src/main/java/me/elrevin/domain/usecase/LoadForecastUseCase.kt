package me.elrevin.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.elrevin.core.other.Constants
import me.elrevin.core.other.lowerOrNull
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class LoadForecastUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(location: Location, existingList: List<Forecast> = listOf()): Flow<Either<List<Forecast>>> = flow {
        val referenceTimeStamp = (System.currentTimeMillis() / 1000).toInt() - Constants.updatePeriod
        val oldData = existingList.find { lowerOrNull(it.lastUpdatedTimestamp, referenceTimeStamp) }
        if (existingList.isEmpty() || oldData != null) {
            // One or several forecast days have not actual data
            emit(Either.loading())
            val loadedData = repository.loadForecast(location)
            if (loadedData.isSuccess()) {
                repository.saveForecast(loadedData.getValue())
            }
            emit(loadedData)
        } else {
            emit(Either.success(existingList))
        }
    }
}