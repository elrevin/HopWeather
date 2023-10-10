package me.elrevin.data.remote

import me.elrevin.core.other.Constants
import me.elrevin.data.remote.dto.CurrentWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") locationName: String,
        @Query("key") apiKey: String = Constants.apiKey
        ): Response<CurrentWeatherDto>
}