package me.elrevin.data.remote

import me.elrevin.core.other.Constants
import me.elrevin.data.remote.dto.ForecastDto
import me.elrevin.data.remote.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") locationName: String,
        @Query("key") apiKey: String = Constants.apiKey
        ): Response<WeatherDto>

    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") locationName: String,
        @Query("days") days: Int = 7,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("key") apiKey: String = Constants.apiKey
        ): Response<ForecastDto>
}