package me.elrevin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("location") var location: LocationDto? = LocationDto(),
    @SerializedName("forecast") var forecast: ForecastDataDto? = ForecastDataDto(),
)

data class ForecastDataDto(
    @SerializedName("forecastday") var forecastday: ArrayList<ForecastDayDto> = arrayListOf()
)

data class ForecastDayDto(
    @SerializedName("date") var date: String? = null,
    @SerializedName("day") var day: DayDto? = DayDto(),
    @SerializedName("astro") var astro: AstroDto? = AstroDto(),
    @SerializedName("hour") var hour: ArrayList<HourDto> = arrayListOf()
)

data class DayDto(
    @SerializedName("maxtemp_c") var maxTemp: Double? = null,
    @SerializedName("mintemp_c") var minTemp: Double? = null,
    @SerializedName("avgtemp_c") var avgTemp: Double? = null,
    @SerializedName("maxwind_kph") var maxWind: Double? = null,
    @SerializedName("totalprecip_mm") var totalPrecip: Int? = null,
    @SerializedName("avghumidity") var avgHumidity: Int? = null,
    @SerializedName("daily_will_it_rain") var dailyWillItRain: Int? = null,
    @SerializedName("daily_chance_of_rain") var dailyChanceOfRain: Int? = null,
    @SerializedName("daily_will_it_snow") var dailyWillItSnow: Int? = null,
    @SerializedName("daily_chance_of_snow") var dailyChanceOfSnow: Int? = null,
    @SerializedName("condition") var condition: ConditionDto? = ConditionDto(),
    @SerializedName("uv") var uv: Int? = null
)

data class AstroDto(
    @SerializedName("sunrise") var sunrise: String? = null,
    @SerializedName("sunset") var sunset: String? = null,
    @SerializedName("moonrise") var moonrise: String? = null,
    @SerializedName("moonset") var moonset: String? = null,
)

data class HourDto(
    @SerializedName("time") var time: String? = null,
    @SerializedName("temp_c") var temp: Double? = null,
    @SerializedName("is_day") var isDay: Int? = null,
    @SerializedName("condition") var condition: ConditionDto? = ConditionDto(),
    @SerializedName("wind_kph") var wind: Double? = null,
    @SerializedName("wind_degree") var windDegree: Int? = null,
    @SerializedName("wind_dir") var windDir: String? = null,
    @SerializedName("pressure_mb") var pressure: Int? = null,
    @SerializedName("precip_mm") var precip: Int? = null,
    @SerializedName("humidity") var humidity: Int? = null,
    @SerializedName("cloud") var cloud: Int? = null,
    @SerializedName("feelslike_c") var feelsLike: Double? = null,
    @SerializedName("will_it_rain") var willItRain: Int? = null,
    @SerializedName("chance_of_rain") var chanceOfRain: Int? = null,
    @SerializedName("will_it_snow") var willItSnow: Int? = null,
    @SerializedName("chance_of_snow") var chanceOfSnow: Int? = null,
    @SerializedName("uv") var uv: Int? = null
)