@file:JvmName("CurrentWeatherKt")

package me.elrevin.data.mapper

import me.elrevin.core.other.dateFormat
import me.elrevin.data.local.entity.CurrentWeatherEntity
import me.elrevin.data.remote.dto.CurrentWeatherDto
import me.elrevin.data.remote.dto.WeatherDto
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather
import java.util.Calendar

fun WeatherDto.toDomainModel(location: Location) = Weather(
    location = location,
    // The forecast data which returns from API contains location,
    // but without url, which converted into id.
    // That why we use location which came in arguments

    currentWeather = this.current!!.toDomainModel(),
    forecasts = this.forecast!!.forecastday.map { it.toDomainModel() }
)

fun me.elrevin.data.local.entity.Weather.toDomainModel() = Weather(
    location = this.location.toDomainModel(),
    currentWeather = this.currentWeather?.toDomainModel(),
    forecasts = this.forecasts.map { it.toDomainModel() }
)

fun Weather.toDataEntity() = me.elrevin.data.local.entity.Weather(
    location = this.location.toDataEntity(),
    currentWeather = this.currentWeather?.toDataEntity(this.location),
    forecasts = this.forecasts.map { it.toDataEntity(this.location) }
)

fun CurrentWeatherEntity.toDomainModel() = CurrentWeather(
    lastUpdated = this.lastUpdated,
    lastUpdatedTimestamp = this.lastUpdatedTimestamp,
    temp = this.temp,
    isDay = this.isDay,
    conditionText = this.conditionText,
    conditionIcon = this.conditionIcon,
    conditionIllustration = this.conditionIllustration,
    wind = this.wind,
    windDegree = this.windDegree,
    windDir = this.windDir,
    pressure = this.pressure,
    precip = this.precip,
    humidity = this.humidity,
    cloud = this.cloud,
    feelslike = this.feelslike,
    gust = this.gust,
)

fun CurrentWeather.toDataEntity(location: Location) = CurrentWeatherEntity(
    locationId = location.id,
    lastUpdated = this.lastUpdated,
    lastUpdatedTimestamp = this.lastUpdatedTimestamp,
    temp = this.temp,
    isDay = this.isDay,
    conditionText = this.conditionText,
    conditionIcon = this.conditionIcon,
    conditionIllustration = this.conditionIllustration,
    wind = this.wind,
    windDegree = this.windDegree,
    windDir = this.windDir,
    pressure = this.pressure,
    precip = this.precip,
    humidity = this.humidity,
    cloud = this.cloud,
    feelslike = this.feelslike,
    gust = this.gust,
)

fun CurrentWeatherDto.toDomainModel() = CurrentWeather(
    temp = temp!!.toInt(),
    lastUpdated = lastUpdated!!.dateFormat(),
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt(),
    isDay = isDay!! == 1,
    conditionText = condition!!.text!!,
    conditionIcon = "https:" + condition!!.icon!!.replace("64x64", "128x128"),
    conditionIllustration = "https://elrevin.me/hop-weather-images/${getConditionIllustration(condition!!.code!!, isDay!! == 1)}.jpg",
    wind = wind!!,
    windDegree = windDegree!!,
    windDir = windDir!!,
    pressure = pressure!!.toInt(),
    precip = precip!!.toInt(),
    humidity = humidity!!,
    cloud = cloud!!,
    feelslike = feelslike!!.toInt(),
    gust = gust!!
)


private fun getConditionIllustration(code: Int, isDay: Boolean): String {
    val season = when(Calendar.getInstance().get(Calendar.MONTH)) {
        0, 1, 11 -> "winter"
        2, 3, 4 -> "spring"
        5, 6, 7 -> "summer"
        else -> "autumn"
    }

    val timeOfDay = if (isDay) "day" else "night"

    val weather = when (code) {
        1000 -> "clear"
        1003 -> "cloudy"
        1006 -> "cloudy"
        1009 -> "overcast"
        1030 -> "fog"
        1063 -> "overcast"
        1066 -> "overcast"
        1069 -> "overcast"
        1072 -> "overcast"
        1087 -> "lightning"
        1114 -> "snow"
        1117 -> "snow"
        1135 -> "fog"
        1147 -> "fog"
        1150 -> "rain"
        1153 -> "rain"
        1168 -> "rain"
        1171 -> "rain"
        1180 -> "rain"
        1183 -> "rain"
        1186 -> "rain"
        1189 -> "rain"
        1192 -> "rain"
        1195 -> "rain"
        1198 -> "rain"
        1201 -> "rain"
        1204 -> "snow"
        1207 -> "snow"
        1210 -> "snow"
        1213 -> "snow"
        1216 -> "snow"
        1219 -> "snow"
        1222 -> "snow"
        1225 -> "snow"
        1237 -> "snow"
        1240 -> "rain"
        1243 -> "rain"
        1246 -> "rain"
        1249 -> "rain"
        1252 -> "rain"
        1255 -> "snow-rain"
        1258 -> "snow-rain"
        1261 -> "snow-rain"
        1264 -> "snow-rain"
        1273 -> "lightning"
        1276 -> "lightning"
        1279 -> "lightning"
        1282 -> "lightning"
        else -> "unknown"
    }

    return "$season-$weather-$timeOfDay"
}