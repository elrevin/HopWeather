package me.elrevin.data.mapper

import me.elrevin.data.remote.dto.WeatherDto
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Location

fun WeatherDto.toCurrentWeatherDomainModel(location: Location) = CurrentWeather(
    location = location,
    temp = current!!.temp!!,
    lastUpdated = current!!.lastUpdated!!.dateFormat(),
    lastUpdatedTimestamp = (System.currentTimeMillis() / 1000).toInt(),
    isDay = current!!.isDay!! == 1,
    conditionText = current!!.condition!!.text!!,
    conditionIcon = current!!.condition!!.icon!!,
    conditionCode = current!!.condition!!.code!!,
    wind = current!!.wind!!,
    windDegree = current!!.windDegree!!,
    windDir = current!!.windDir!!,
    pressure = current!!.pressure!!,
    precip = current!!.precip!!,
    humidity = current!!.humidity!!,
    cloud = current!!.cloud!!,
    feelslike = current!!.feelslike!!,
    gust = current!!.gust!!
)
