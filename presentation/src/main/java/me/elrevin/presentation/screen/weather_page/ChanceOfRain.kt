package me.elrevin.presentation.screen.weather_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.elrevin.core.other.extractHours
import me.elrevin.domain.model.Forecast
import me.elrevin.presentation.base_ui.theme.AppTheme
import java.time.LocalDateTime

@Composable
internal fun ChanceOfRain(forecast: Forecast, nextDayforecast: Forecast) {
    val now = LocalDateTime.now().hour
    var hours = forecast.onlyActualHours.toMutableList()
    hours.addAll(nextDayforecast.hours)
    hours = hours.subList(0, 12)

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
    ) {
        LargePanel(icon = AppTheme.icons.rainy(), title = "Chance of rain") {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (hour in hours) {
                    val h = hour.time.extractHours()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier.width(48.dp)
                        ) {
                            Text(
                                text = if (h == now) "NOW" else (h.toString() + if (h < 12) "AM" else "PM"),
                                style = AppTheme.typography.LargeLabel,
                                color = AppTheme.colors.panelText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(24.dp)
                                .background(
                                    AppTheme.colors.chanceBarBackground,
                                    RoundedCornerShape(50)
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(
                                        hour.chanceOfRain / 100f
                                    )
                                    .height(24.dp)
                                    .background(
                                        AppTheme.colors.chanceBarForeground,
                                        RoundedCornerShape(50)
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier.width(32.dp)
                        ) {
                            Text(
                                text = "${hour.chanceOfRain}%",
                                style = AppTheme.typography.LargeLabel,
                                color = AppTheme.colors.panelText
                            )
                        }
                    }

                }
            }
        }
    }
}