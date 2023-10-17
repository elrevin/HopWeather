package me.elrevin.domain.model

data class Weather(
    val location: Location,
    val currentWeather: CurrentWeather? = null,
    val forecasts: List<Forecast> = listOf(),
) {
    val dayTemp: Int
        get() = forecasts[0].maxTemp

    val nightTemp: Int
        get() = forecasts[0].minTemp
}
