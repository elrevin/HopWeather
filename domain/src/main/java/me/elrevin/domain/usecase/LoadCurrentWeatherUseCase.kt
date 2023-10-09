package me.elrevin.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.elrevin.core.other.Constants
import me.elrevin.core.other.lowerOrNull
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class LoadCurrentWeatherUseCase(private val repository: WeatherRepository) {

    /**
     * Load current weather data for the location, if it needed
     *
     * @param location
     */
    suspend operator fun invoke(location: Location, existingCurrentWeather: CurrentWeather? = null): Flow<Either<CurrentWeather>>  = flow{
        // if we have no existingCurrentWeather or it is not actual, lets try to load
        if (lowerOrNull(existingCurrentWeather?.lastUpdatedTimestamp, (System.currentTimeMillis() / 1000).toInt() - Constants.updatePeriod)) {
            emit(Either.loading())
            val loadedData = repository.loadCurrentWeather(location)
            if (loadedData.isSuccess()) {
                repository.saveCurrentWeather(loadedData.getValue())
            }
            emit(loadedData)
        } else {
            emit(Either.success(existingCurrentWeather!!))
        }
    }
}