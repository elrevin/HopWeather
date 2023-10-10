package me.elrevin.data.mapper

import me.elrevin.data.remote.dto.HourDto
import me.elrevin.domain.model.HourForecast

fun HourDto.toDomainModel() = HourForecast(
    time = this.time!!.extractTime(),
    temp = this.temp!!,
    isDay = this.isDay!! == 1,
    conditionText = this.condition!!.text!!,
    conditionIcon = this.condition!!.icon!!,
    conditionCode = this.condition!!.code!!,
    wind = this.wind!!,
    windDegree = this.windDegree!!,
    windDir = this.windDir!!,
    pressure = this.pressure!!,
    precip = this.precip!!,
    humidity = this.humidity!!,
    cloud = this.cloud!!,
    feelsLike = this.feelsLike!!,
    willItRain = this.willItRain!!,
    chanceOfRain = this.chanceOfRain!!,
    willItSnow = this.willItSnow!!,
    chanceOfSnow = this.chanceOfSnow!!,
    uv = this.uv!!,
)