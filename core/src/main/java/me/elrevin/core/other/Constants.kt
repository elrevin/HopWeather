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

        fun getErrorByCode(code: String): String = when (code) {
            "networkError" -> "Network connection was lost."
            "deviceCurrentLocationNotFound" -> "Your location not found."
            "apiError1002" -> "API key not provided."
            "apiError1003" -> "The developer is dummy."
            "apiError1005" -> "The developer is dummy."
            "apiError1006" -> "No location found<"
            "apiError2006" -> "The developer is dummy."
            "apiError2007" -> "Time to pay for information."
            "apiError2008" -> "You are banned."
            "apiError2009" -> "You pay so few, need more."
            "apiError9999" -> "Server is broken."
            else -> "Unknown error."
        }

    }
}