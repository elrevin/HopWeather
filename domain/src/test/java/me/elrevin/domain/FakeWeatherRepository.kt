package me.elrevin.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather
import me.elrevin.domain.repository.WeatherRepository

class FakeWeatherRepository : WeatherRepository {
    private val dataWeather = mutableListOf<Weather>()

    private val flowWeather = MutableStateFlow<Weather?>(null)

    private suspend fun emitWeather(location: Location) {
        val value = dataWeather.find { location.id == it.location.id }
        flowWeather.emit(value)
    }

    suspend fun addFakeWeather(location: Location): Weather {
        val current = fakeCurrentWeather(location)
        val forecasts = listOf<Forecast>(
            fakeForecast(location, 1),
            fakeForecast(location, 2),
            fakeForecast(location, 3),
            fakeForecast(location, 4),
        )
        val weather = Weather(
            location = location,
            currentWeather = current,
            forecasts = forecasts
        )
        dataWeather.add(weather)
        emitWeather(location)
        return weather
    }

    override fun getWeather(location: Location): Flow<Weather?> = flowWeather

    override suspend fun loadWeather(location: Location): Either<Weather> {
        delay(2000)
        if (location.id == "loc4") {
            return Either.failure("apiError1006")
        }

        return Either.success(
            Weather(
                location = location,
                currentWeather = fakeCurrentWeather(location).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt()),
                forecasts = listOf(
                    fakeForecast(location, 1),
                    fakeForecast(location, 2),
                    fakeForecast(location, 3),
                    fakeForecast(location, 4),
                )
            )
        )

    }

    override suspend fun saveWeather(weather: Weather) {
        dataWeather.removeIf { it.location.id == weather.location.id }
        dataWeather.add(weather)
        emitWeather(weather.location)
    }
}

fun fakeCurrentWeather(location: Location) = CurrentWeather(
    lastUpdated = "",
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt() - (if (location.id == "loc3") 0 else 30 * 60 + 2),
    temp = (0..30).random(),
    isDay = true,
    conditionText = "",
    conditionIcon = "",
    conditionIllustration = "",
    wind = 0.0,
    windDegree = 0,
    windDir = "",
    pressure = 0,
    precip = 0,
    humidity = 0,
    cloud = 0,
    feelslike = 0,
    gust = 0.0
)

fun fakeForecast(location: Location, id: Int) = Forecast(
    id = id,
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt() - (if (location.id == "loc3") 0 else 30 * 60 + 2),
    date = "",
    dateIso = "",
    maxTemp = 0,
    minTemp = 0,
    avgTemp = 0,
    maxWind = 0.0,
    totalPrecip = 0.0,
    avgHumidity = 0.0,
    dailyWillItRain = false,
    dailyChanceOfRain = 0,
    dailyWillItSnow = false,
    dailyChanceOfSnow = 0,
    conditionText = "",
    conditionIcon = "",
    conditionCode = 0,
    uv = 0.0,
    hours = listOf(),
    sunrise = "",
    sunset = "",
)