package me.elrevin.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.elrevin.core.other.Constants
import me.elrevin.core.other.lowerOrNull
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather
import me.elrevin.domain.repository.WeatherRepository

class LoadWeatherUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(location: Location, existingWeather: Weather?): Flow<Either<Weather>> = flow {
        val referenceTimeStamp = (System.currentTimeMillis() / 1000).toInt() - Constants.updatePeriod
        val oldData = lowerOrNull(existingWeather?.currentWeather?.lastUpdatedTimestamp, referenceTimeStamp)
        if (oldData) {
            // One or several forecast days have not actual data
            emit(Either.loading(if(existingWeather?.currentWeather != null) existingWeather else null))
            val loadedData = repository.loadWeather(location)
            if (loadedData.isSuccess()) {
                repository.saveWeather(loadedData.getValue())
            }
            emit(loadedData)
        } else {
            emit(Either.success(existingWeather!!))
        }
    }
}