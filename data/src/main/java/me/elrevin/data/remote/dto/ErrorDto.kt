package me.elrevin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(
    @SerializedName("error") val error: ErrorDto? = null
)

data class ErrorDto (
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null,
)
