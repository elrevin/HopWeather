package me.elrevin.core.other

import me.elrevin.recipes_presentation.BuildConfig

object Constants {
    const val apiBaseUrl = "https://api.weatherapi.com/v1/"
    const val apiKey = BuildConfig.apiKey

    const val updatePeriod = 30 * 60

    object Errors {
        const val unknown = "unknown"
        const val network = "networkError"
        const val deviceCurrentLocation = "deviceCurrentLocationNotFound"

        fun apiError(code: String) = "apiError$code"
    }
}