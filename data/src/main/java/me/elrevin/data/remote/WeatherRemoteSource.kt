package me.elrevin.data.remote

import me.elrevin.core.other.Constants
import me.elrevin.data.remote.dto.ErrorResponseDto
import me.elrevin.data.remote.dto.WeatherDto
import me.elrevin.domain.model.Either
import retrofit2.Retrofit

class WeatherRemoteSource (
    private val api: WeatherApi,
    private val retrofit: Retrofit
) {
    suspend fun loadWeather(locationQuery: String): Either<WeatherDto> =
        try {
            api.loadWeather(locationQuery).let { response->
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Either.success(response.body()!!)
                    } else {
                        Either.failure(Constants.Errors.unknown)
                    }
                } else {
                    val errorBody = response.errorBody()
                    if (errorBody == null) {
                        Either.failure(Constants.Errors.unknown)
                    } else {
                        val error = retrofit.responseBodyConverter<ErrorResponseDto>(
                            ErrorResponseDto::class.java,
                            arrayOf()
                        ).convert(errorBody)

                        Either.failure(error?.error?.code?.let { "apiError$it" }
                            ?: Constants.Errors.unknown)

                    }
                }
            }
        } catch (e: Exception) {
            Either.failure("networkError")
        }
}