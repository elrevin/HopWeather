package me.elrevin.data

import kotlinx.coroutines.runBlocking
import me.elrevin.data.remote.LocationApi
import me.elrevin.data.remote.LocationRemoteSource
import me.elrevin.data.remote.dto.LocationDto
import me.elrevin.domain.model.Either
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationSourceTests {
    private lateinit var server: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var api: LocationApi
    private lateinit var locationRemoteSource: LocationRemoteSource

    @Before
    fun up() {
        server = MockWebServer()
        server.start()
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(LocationApi::class.java)
        locationRemoteSource = LocationRemoteSource(api, retrofit)
    }

    @Test
    fun `Successfully get locations by name`() {
        val res = MockResponse()
        res.setBody(searchLocationNotEmptyData())
        server.enqueue(res)
        val data: Either<List<LocationDto>>
        runBlocking {
            data = locationRemoteSource.searchLocation("")
        }
        Assert.assertEquals(data.isSuccess(), true)
        Assert.assertEquals(data.getValue().size, 5)
        Assert.assertEquals(data.getValue()[0].name, "Berlin")
        Assert.assertEquals(data.getValue()[0].url, "berlin-berlin-germany")
    }

    @Test
    fun `Successfully get empty locations name by name`() {
        val res = MockResponse()
        res.setBody(searchLocationEmptyData())
        server.enqueue(res)
        val data: Either<List<LocationDto>>
        runBlocking {
            data = locationRemoteSource.searchLocation("")
        }
        Assert.assertEquals(data.isSuccess(), true)
        Assert.assertEquals(data.getValue().size, 0)
    }

    @Test
    fun `Api key error`() {
        val res = MockResponse()
        res.setBody(getApiKeyErrorData())
        res.setResponseCode(403)
        server.enqueue(res)
        val data: Either<List<LocationDto>> = runBlocking {
            locationRemoteSource.searchLocation("")
        }
        Assert.assertEquals(data.isFailure(), true)
        Assert.assertEquals(data.getFailureMsgOrNull(), "apiError2008")
    }


    @Test
    fun `Network error`() {
        server.shutdown()
        val data: Either<List<LocationDto>> = runBlocking {
            locationRemoteSource.searchLocation("")
        }
        Assert.assertEquals(data.isFailure(), true)
        Assert.assertEquals(data.getFailureMsgOrNull(), "networkError")
    }


    @After
    fun down() {
        server.shutdown()
    }
}