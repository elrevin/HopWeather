package me.elrevin.data

import kotlinx.coroutines.runBlocking
import me.elrevin.data.remote.WeatherApi
import me.elrevin.data.remote.WeatherRemoteSource
import me.elrevin.data.remote.dto.CurrentWeatherDto
import me.elrevin.domain.model.Either
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class `Current weather source tests` {
    private lateinit var server: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var api: WeatherApi
    private lateinit var weatherRemoteSource: WeatherRemoteSource

    @Before
    fun up() {
        server = MockWebServer()
        server.start()
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(WeatherApi::class.java)
        weatherRemoteSource = WeatherRemoteSource(api, retrofit)
    }

    @Test
    fun `Successfully get current weather`() {
        val res = MockResponse()
        res.setBody(getSuccessfulCurrentWeatherData())
        server.enqueue(res)
        val data: Either<CurrentWeatherDto>
        runBlocking {
            data = weatherRemoteSource.loadCurrentWeather("")
        }
        Assert.assertEquals(data.isSuccess(), true)
        Assert.assertEquals(data.getValue().location?.name, "Berlin")
        Assert.assertEquals(data.getValue().current?.temp, 15.0)
    }

    @Test
    fun `Get location not found error`() {
        val res = MockResponse()
        res.setBody(getLocationNotFoundErrorData())
        res.setResponseCode(400)
        server.enqueue(res)
        val data: Either<CurrentWeatherDto> = runBlocking {
            weatherRemoteSource.loadCurrentWeather("")
        }
        Assert.assertEquals(data.isFailure(), true)
        Assert.assertEquals(data.getFailureMsgOrNull(), "apiError1006")
    }

    @Test
    fun `Network error`() {
        server.shutdown()
        val data: Either<CurrentWeatherDto> = runBlocking {
            weatherRemoteSource.loadCurrentWeather("")
        }
        Assert.assertEquals(data.isFailure(), true)
        Assert.assertEquals(data.getFailureMsgOrNull(), "networkError")
    }

    @After
    fun down() {
        server.shutdown()
    }
}

