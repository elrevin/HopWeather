package me.elrevin.data.mapper

import me.elrevin.data.remote.dto.AstroDto
import me.elrevin.domain.model.AstroForecast

fun AstroDto.toDomainModel() = AstroForecast(
    sunrise = this.sunrise!!,
    sunset = this.sunset!!
)