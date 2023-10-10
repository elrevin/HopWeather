package me.elrevin.domain

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository
import me.elrevin.domain.usecase.GetCurrentWeatherUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetCurrentWeatherUseCaseTest {
    private val repository = FakeRepository()
    private val useCase = GetCurrentWeatherUseCase(repository)

    @Test
    fun `Get current weather with loading first`() {
        runBlocking {
            // Empty data, request current weather for first location
            var iteration = 0
            var location = Location(id = "loc1")
            launch {
                useCase(location).collect{
                    if (iteration == 0) {
                        assertTrue(it.isLoading())
                    } else {
                        assertTrue(it.isSuccess())
                        assertEquals(it.getValue().location, location)
                        cancel()
                    }
                    iteration++
                }
            }
        }
    }

    @Test
    fun `Get current weather from db`() {
        var location = Location(id = "loc3")

        runBlocking {
            launch {
                repository.addFake(location)
                useCase(location).collect{
                    assertTrue(it.isSuccess())
                    assertEquals(it.getValue().location, location)
                    cancel()
                }
            }
        }
    }

    @Test
    fun `Get current weather from server with API failure`() {
        var location = Location(id = "loc4")
        var iteration = 0

        runBlocking {
            launch {
                useCase(location).collect{
                    if (iteration == 0) {
                        assertTrue(it.isLoading())
                    } else {
                        assertTrue(it.isFailure())
                        assertEquals(it.getFailureMsgOrNull(), "apiError1006")
                        cancel()

                    }
                    iteration++
                }
            }
        }
    }

    @Test
    fun `In DB is not actual data`() {
        var location = Location(id = "loc2")
        var iteration = 0

        runBlocking {
            launch {
                val fake = repository.addFake(location)
                useCase(location).collect{
                    if (iteration == 0) {
                        assertTrue(it.isLoading())
                    } else {
                        assertTrue(it.isSuccess())
                        assertEquals(it.getValue().location, location)
                        assertTrue(it.getValue().lastUpdatedTimestamp - fake.lastUpdatedTimestamp > 30 * 60 + 2)
                        cancel()

                    }
                    iteration++
                }
            }
        }
    }
}

private class FakeRepository: WeatherRepository {
    private val data = mutableListOf<CurrentWeather>()

    private val flow = MutableStateFlow<CurrentWeather?>(null)

    private suspend fun emit(location: Location) {
        val value = data.find { location.id == it.location.id }
        flow.emit(value)
    }

    suspend fun addFake(location: Location): CurrentWeather {
        val item = fakeCurrentWeather(location)
        data.add(item)
        emit(location)
        return item
    }

    override fun getCurrentWeather(location: Location): Flow<CurrentWeather?> = flow

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) {
        data.removeIf {
            currentWeather.location.id == it.location.id
        }

        data.add(currentWeather)

        emit(currentWeather.location)
    }

    override suspend fun loadCurrentWeather(location: Location): Either<CurrentWeather> {
        delay(2000)
        if (location.id == "loc4") {
            return Either.failure("apiError1006")
        }

        return Either.success(fakeCurrentWeather(location).copy(lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt()))
    }
}

fun fakeCurrentWeather(location: Location) = CurrentWeather(
    location = location,
    lastUpdated = "",
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt() - (if (location.id == "loc3") 0 else 30 * 60 + 2),
    temp = (0 .. 30).random().toDouble(),
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
    gust = 0.0,

)