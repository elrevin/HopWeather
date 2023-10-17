package me.elrevin.domain

import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.elrevin.domain.model.Location
import me.elrevin.domain.usecase.GetWeatherUseCase
import org.junit.Assert
import org.junit.Test

class GetWeatherUseCaseTests {
    private val repository = FakeWeatherRepository()
    private val useCase = GetWeatherUseCase(repository)

    @Test
    fun `Get weather with loading first`() {
        runBlocking {
            // Empty data, request current weather for first location
            var iteration = 0
            var location = Location(id = "loc3")
            launch {
                useCase(location).collect{
                    if (iteration == 0) {
                        Assert.assertTrue(it.isLoading())
                    } else {
                        Assert.assertTrue(it.isSuccess())
                        Assert.assertEquals(location, it.getValue().location)
                        Assert.assertNotEquals(null, it.getValue().currentWeather)
                        Assert.assertEquals(4, it.getValue().forecasts.size)
                        cancel()
                    }
                    iteration++
                }
            }
        }
    }

    @Test
    fun `Get weather from db`() {
        var location = Location(id = "loc3")

        runBlocking {
            launch {
                repository.addFakeWeather(location)
                useCase(location).collect{
                    Assert.assertTrue(it.isSuccess())
                    Assert.assertNotEquals(null, it.getValue().currentWeather)
                    Assert.assertEquals(4, it.getValue().forecasts.size)
                    cancel()
                }
            }
        }
    }

    @Test
    fun `Get weather from server with API failure`() {
        var location = Location(id = "loc4")
        var iteration = 0

        runBlocking {
            launch {
                useCase(location).collect{
                    if (iteration == 0) {
                        Assert.assertTrue(it.isLoading())
                    } else {
                        Assert.assertTrue(it.isFailure())
                        Assert.assertEquals("apiError1006", it.getFailureMsgOrNull())
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
                        Assert.assertTrue(it.isLoading())
                    } else {
                        Assert.assertTrue(it.isSuccess())
                        Assert.assertEquals(it.getValue().forecasts.size, 4)
                        Assert.assertTrue(it.getValue().currentWeather!!.lastUpdatedTimestamp - fake.currentWeather!!.lastUpdatedTimestamp > 30 * 60 + 2)
                        cancel()

                    }
                    iteration++
                }
            }
        }
    }
}