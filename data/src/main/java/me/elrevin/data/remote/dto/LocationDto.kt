package me.elrevin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("url") val url: String? = null,
)
