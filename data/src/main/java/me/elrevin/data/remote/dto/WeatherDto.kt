package me.elrevin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("location") var location: LocationDto? = LocationDto(),
    @SerializedName("current") var current: CurrentDto? = CurrentDto()
)

