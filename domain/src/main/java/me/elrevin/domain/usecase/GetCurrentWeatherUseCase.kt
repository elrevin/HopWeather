package me.elrevin.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.elrevin.core.other.Constants
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(listOfLocations: List<Location>): Flow<List<Either<CurrentWeather>>> = repository
        .getCurrentWeather()
        .map { list ->
            val resultList: List<Either<CurrentWeather>> = listOfLocations.map { location ->
                // For each requested location must be current weather in the data base
                // and is have to be fresh
                val weather = list.find { weather -> weather.location.id == location.id }
                if (weather == null || weather.lastUpdatedTimestamp > (System.currentTimeMillis() / 1000).toInt() + Constants.updatePeriod) {

                    // if data about current weather does not exist for this location, lets load it
                    repository.loadCurrentWeather(location)
                } else {
                    Either.success(weather)
                }
            }

            // Prepare loaded data
            val toSave = resultList.filter {
                it.isSuccess() && list.find { item -> item.location.id == it.getValue().location.id && item.lastUpdated == it.getValue().lastUpdated } == null
            }.map { it.getValue() }

            repository.saveCurrentWeather(toSave)

            resultList
        }
}