package me.elrevin.data.mapper

import me.elrevin.core.other.extractTime
import me.elrevin.data.remote.dto.HourDto
import me.elrevin.domain.model.HourForecast

fun HourDto.toDomainModel() = HourForecast(
    time = this.time!!.extractTime(),
    temp = this.temp!!.toInt(),
    isDay = this.isDay!! == 1,
    conditionText = this.condition!!.text!!,
    conditionIcon = "https:" + this.condition!!.icon!!,
    conditionCode = this.condition!!.code!!,
    wind = this.wind!!,
    windDegree = this.windDegree!!,
    windDir = this.windDir!!,
    pressure = this.pressure!!.toInt(),
    precip = this.precip!!.toInt(),
    humidity = this.humidity!!,
    cloud = this.cloud!!,
    feelsLike = this.feelsLike!!.toInt(),
    willItRain = this.willItRain!!,
    chanceOfRain = this.chanceOfRain!!,
    willItSnow = this.willItSnow!!,
    chanceOfSnow = this.chanceOfSnow!!,
    uv = this.uv!!,
)