package me.elrevin.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class FakeWeatherRepository : WeatherRepository {
    private val dataWeather = mutableListOf<CurrentWeather>()

    private val flowWeather = MutableStateFlow<CurrentWeather?>(null)

    private suspend fun emitWeather(location: Location) {
        val value = dataWeather.find { location.id == it.location.id }
        flowWeather.emit(value)
    }

    suspend fun addFakeWeather(location: Location): CurrentWeather {
        val item = fakeCurrentWeather(location)
        dataWeather.add(item)
        emitWeather(location)
        return item
    }

    override fun getCurrentWeather(location: Location): Flow<CurrentWeather?> = flowWeather

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) {
        dataWeather.removeIf {
            currentWeather.location.id == it.location.id
        }

        dataWeather.add(currentWeather)

        emitWeather(currentWeather.location)
    }

    override suspend fun loadCurrentWeather(location: Location): Either<CurrentWeather> {
        delay(2000)
        if (location.id == "loc4") {
            return Either.failure("apiError1006")
        }

        return Either.success(fakeCurrentWeather(location).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt()))
    }

    private val dataForecast = mutableListOf<Forecast>()

    private val flowForecast = MutableStateFlow<List<Forecast>>(listOf())

    private suspend fun emitForecast(location: Location) {
        val values = dataForecast.filter { location.id == it.location.id }
        flowForecast.emit(values)
    }

    suspend fun addFakeForecast(location: Location, id: Int): Forecast {
        val item = fakeForecast(location, id)
        dataForecast.add(item)
        emitForecast(location)
        return item
    }

    override fun getForecast(location: Location): Flow<List<Forecast>> = flowForecast

    override suspend fun loadForecast(location: Location): Either<List<Forecast>> {
        delay(2000)
        if (location.id == "loc4") {
            return Either.failure("apiError1006")
        }

        return Either.success(
            listOf(
                fakeForecast(location, 1).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt()),
                fakeForecast(location, 2).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt()),
                fakeForecast(location, 3).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt()),
                fakeForecast(location, 4).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt())
            )
        )

    }

    override suspend fun saveForecast(list: List<Forecast>) {
        val location = list.getOrNull(0)?.location
        if (location != null) {
            dataForecast.removeIf {
                it.location.id == location.id
            }

            list.forEach {
                dataForecast.add(it)
            }

            emitForecast(location)
        }
    }
}

fun fakeCurrentWeather(location: Location) = CurrentWeather(
    location = location,
    lastUpdated = "",
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt() - (if (location.id == "loc3") 0 else 30 * 60 + 2),
    temp = (0..30).random().toDouble(),
    isDay = true,
    conditionText = "",
    conditionIcon = "",
    conditionCode = 0,
    wind = 0.0,
    windDegree = 0,
    windDir = "",
    pressure = 0,
    precip = 0,
    humidity = 0,
    cloud = 0,
    feelslike = 0.0,
    gust = 0.0
)

fun fakeForecast(location: Location, id: Int) = Forecast(
id = id,
location = location,
lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt() - (if (location.id == "loc3") 0 else 30 * 60 + 2),
date = "",
dateIso = "",
maxTemp = 0.0,
minTemp = 0.0,
avgTemp = 0.0,
maxWind = 0.0,
totalPrecip = 0,
avgHumidity = 0,
dailyWillItRain = 0,
dailyChanceOfRain = 0,
dailyWillItSnow = 0,
dailyChanceOfSnow = 0,
conditionText = "",
conditionIcon = "",
conditionCode = 0,
uv = 0,
hours = listOf(),
sunrise = "",
sunset = "",
)