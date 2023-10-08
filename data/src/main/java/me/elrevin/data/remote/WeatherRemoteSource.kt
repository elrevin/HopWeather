package me.elrevin.data.remote

import me.elrevin.data.remote.dto.CurrentWeatherDto
import me.elrevin.domain.model.Either

interface WeatherRemoteSource {
    suspend fun loadCurrentWeather(locationQuery: String): Either<CurrentWeatherDto>
}