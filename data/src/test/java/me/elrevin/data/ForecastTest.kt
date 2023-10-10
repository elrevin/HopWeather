package me.elrevin.data

import kotlinx.coroutines.runBlocking
import me.elrevin.data.mapper.toDomainModel
import me.elrevin.data.remote.WeatherApi
import me.elrevin.data.remote.WeatherRemoteSource
import me.elrevin.data.remote.dto.ForecastDto
import me.elrevin.data.remote.dto.WeatherDto
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastTest {
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
    fun `Successfully get forecast`() {
        val res = MockResponse()
        res.setBody(forecastTestData())
        server.enqueue(res)
        val data: Either<ForecastDto> = runBlocking {
            weatherRemoteSource.loadForecast("")
        }
        Assert.assertEquals(true, data.isSuccess())
        Assert.assertEquals("London", data.getValue().location?.name)
        Assert.assertEquals(1, data.getValue().forecast?.forecastday?.size)
        Assert.assertEquals("2023-10-10", data.getValue().forecast!!.forecastday[0].date)
        Assert.assertEquals(24, data.getValue().forecast!!.forecastday[0].hour.size)

        val domainModelList = data.getValue().toDomainModel(Location("loc1"))
        Assert.assertEquals(1, domainModelList.size)
        Assert.assertEquals(24, domainModelList[0].hours.size)
        Assert.assertEquals("10.10.2023", domainModelList[0].date)
        Assert.assertEquals("Sunny", domainModelList[0].conditionText)

    }


    @Test
    fun `Get location not found error`() {
        val res = MockResponse()
        res.setBody(getLocationNotFoundErrorData())
        res.setResponseCode(400)
        server.enqueue(res)
        val data: Either<ForecastDto> = runBlocking {
            weatherRemoteSource.loadForecast("")
        }
        Assert.assertEquals(data.isFailure(), true)
        Assert.assertEquals(data.getFailureMsgOrNull(), "apiError1006")
    }


    @Test
    fun `Network error`() {
        server.shutdown()
        val data: Either<ForecastDto> = runBlocking {
            weatherRemoteSource.loadForecast("")
        }
        Assert.assertEquals(data.isFailure(), true)
        Assert.assertEquals(data.getFailureMsgOrNull(), "networkError")
    }


    @After
    fun down() {
        server.shutdown()
    }
}