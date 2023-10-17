package me.elrevin.presentation.screen.weather_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.elrevin.domain.model.Forecast
import me.elrevin.presentation.base_ui.theme.AppTheme

internal fun LazyListScope.DayForecast(dayForecast: Forecast) {
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallPanel(
                icon = AppTheme.icons.wind(),
                title = "Wind speed",
                value = "${dayForecast.maxWind} km/h"
            )
            SmallPanel(
                icon = AppTheme.icons.rainy(),
                title = "Rain chance",
                value = "${dayForecast.dailyChanceOfRain} %"
            )
        }
    }

    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallPanel(
                icon = AppTheme.icons.pressure(),
                title = "Pressure",
                value = "${dayForecast.maxPressure} mb"
            )
            SmallPanel(
                icon = AppTheme.icons.uvIndex(),
                title = "UV Index",
                value = "${dayForecast.uv}"
            )
        }
    }

    item {
        HourlyForecast(forecast = dayForecast)
    }
    
    item { 
        ChanceOfRain(forecast = dayForecast)
    }

    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallPanel(
                icon = AppTheme.icons.sunrise(),
                title = "Sunrise",
                value = dayForecast.sunrise
            )
            SmallPanel(
                icon = AppTheme.icons.sunset(),
                title = "Sunset",
                value = dayForecast.sunset
            )
        }
    }
}


