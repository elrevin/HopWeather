package me.elrevin.domain

import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.elrevin.domain.model.Location
import me.elrevin.domain.usecase.GetForecastUseCase
import org.junit.Assert
import org.junit.Test

class GetForecastUseCaseTests {
    private val repository = FakeWeatherRepository()
    private val useCase = GetForecastUseCase(repository)

    @Test
    fun `Get forecast with loading first`() {
        runBlocking {
            // Empty data, request current weather for first location
            var iteration = 0
            var location = Location(id = "loc1")
            launch {
                useCase(location).collect{
                    if (iteration == 0) {
                        Assert.assertTrue(it.isLoading())
                    } else {
                        Assert.assertTrue(it.isSuccess())
                        Assert.assertEquals(4, it.getValue().size)
                        cancel()
                    }
                    iteration++
                }
            }
        }
    }

    @Test
    fun `Get forecast from db`() {
        var location = Location(id = "loc3")

        runBlocking {
            launch {
                repository.addFakeForecast(location, 1)
                repository.addFakeForecast(location, 2)
                repository.addFakeForecast(location, 3)
                useCase(location).collect{
                    Assert.assertTrue(it.isSuccess())
                    Assert.assertEquals(3, it.getValue().size)
                    cancel()
                }
            }
        }
    }

    @Test
    fun `Get forecast from server with API failure`() {
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
                val fake1 = repository.addFakeForecast(location, 1)
                val fake2 = repository.addFakeForecast(location, 2)
                val fake3 = repository.addFakeForecast(location, 3)
                val fake4 = repository.addFakeForecast(location, 4)
                useCase(location).collect{
                    if (iteration == 0) {
                        Assert.assertTrue(it.isLoading())
                    } else {
                        Assert.assertTrue(it.isSuccess())
                        Assert.assertEquals(it.getValue().size, 4)
                        Assert.assertTrue(it.getValue()[0].lastUpdatedTimestamp - fake1.lastUpdatedTimestamp > 30 * 60 + 2)
                        Assert.assertTrue(it.getValue()[1].lastUpdatedTimestamp - fake2.lastUpdatedTimestamp > 30 * 60 + 2)
                        Assert.assertTrue(it.getValue()[2].lastUpdatedTimestamp - fake3.lastUpdatedTimestamp > 30 * 60 + 2)
                        Assert.assertTrue(it.getValue()[3].lastUpdatedTimestamp - fake4.lastUpdatedTimestamp > 30 * 60 + 2)
                        cancel()

                    }
                    iteration++
                }
            }
        }
    }
}