package me.elrevin.presentation.screen.weather_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.elrevin.core.other.extractHours
import me.elrevin.domain.model.Forecast
import me.elrevin.domain.model.HourForecast
import me.elrevin.presentation.base_ui.theme.AppTheme
import java.time.LocalDateTime

@Composable
internal fun HourlyForecast(forecast: Forecast) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
    ) {
        val now = LocalDateTime.now().hour
        val hours = forecast.onlyActualHours.subList(0, 6.coerceAtMost(forecast.onlyActualHours.size))
        LargePanel(icon = AppTheme.icons.watch(), title = "Hourly forecast") {
            Row (
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (hour in hours) {
                    HourForecast(
                        hour = hour,
                        now = now
                    )
                }
            }
        }
    }
}

@Composable
private fun HourForecast(
    hour: HourForecast,
    now: Int
) {
    val h = hour.time.extractHours()
    Box(contentAlignment = Alignment.TopCenter) {
        Row (verticalAlignment = Alignment.Bottom) {
            Text(
                text = if (h == now) "NOW" else h.toString(),
                style = AppTheme.typography.LargeLabel,
                color = AppTheme.colors.panelText
            )
            if (h != now) {
                Text(
                    text = if (h < 12) "AM" else "PM",
                    style = AppTheme.typography.SmallLabel,
                    color = AppTheme.colors.panelText
                )

            }
        }

        AsyncImage(
            modifier = Modifier
                .padding(top = 18.dp)
                .size(32.dp),
            model = hour.conditionIcon,
            contentDescription = hour.conditionText
        )

        Text(
            modifier = Modifier
                .padding(top = 54.dp),
            text = "${hour.temp}°",
            style = AppTheme.typography.Value,
            color = AppTheme.colors.panelText
        )
    }

}
