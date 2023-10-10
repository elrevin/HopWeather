package me.elrevin.domain

import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.elrevin.domain.model.Location
import me.elrevin.domain.usecase.GetCurrentWeatherUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetCurrentWeatherUseCaseTest {
    private val repository = FakeWeatherRepository()
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
                repository.addFakeWeather(location)
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
                val fake = repository.addFakeWeather(location)
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