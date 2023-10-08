package me.elrevin.domain.model

data class CurrentWeather(
    val lastUpdated: String = "",
    val location: Location,
    val temp: Int = 0,
    val isDay: Int = 0,
    val conditionText: String = "",
    val conditionIcon: String = "",
    val conditionCode: Int = 0,
    val wind: Double = 0.0,
    val windDegree: Int = 0,
    val windDir: String = "",
    val pressure: Int = 0,
    val precip: Int = 0,
    val humidity: Int = 0,
    val cloud: Int = 0,
    val feelslike: Double = 0.0,
    val gust: Double = 0.0
)
