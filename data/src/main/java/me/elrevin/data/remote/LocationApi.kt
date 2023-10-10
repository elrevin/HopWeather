package me.elrevin.data.remote

import me.elrevin.core.other.Constants
import me.elrevin.data.remote.dto.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("search.json")
    suspend fun searchLocation(
        @Query("q") locationName: String,
        @Query("key") apiKey: String = Constants.apiKey,
    ): Response<List<LocationDto>>
}