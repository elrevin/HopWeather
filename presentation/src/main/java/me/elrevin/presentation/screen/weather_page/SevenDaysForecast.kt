package me.elrevin.presentation.screen.weather_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.elrevin.core.other.toLocalDate
import me.elrevin.domain.model.Forecast
import me.elrevin.presentation.base_ui.theme.AppTheme
import java.time.format.DateTimeFormatter
import java.util.Locale

internal fun LazyListScope.SevenDaysForecast(forecasts: List<Forecast>) {
    items(forecasts.size) { index ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.panelBackground)
                .padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = if (index == 0) "Today" else forecasts[index].dateIso.toLocalDate()
                        .format(DateTimeFormatter.ofPattern("E, MMM dd", Locale.US)),
                    style = AppTheme.typography.Title,
                    color = AppTheme.colors.panelText
                )
                Text(
                    text = forecasts[index].conditionText,
                    style = AppTheme.typography.Value,
                    color = AppTheme.colors.panelText
                )
            }
            Column(
                modifier = Modifier.width(32.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "${forecasts[index].maxTemp}°",
                    style = AppTheme.typography.Title,
                    color = AppTheme.colors.panelText
                )
                Text(
                    text = "${forecasts[index].minTemp}°",
                    style = AppTheme.typography.Title,
                    color = AppTheme.colors.panelText
                )
            }
            
            Box(modifier = Modifier
                .size(1.dp, 48.dp)
                .background(AppTheme.colors.divider))

            AsyncImage(
                model = forecasts[index].conditionIcon,
                contentDescription = forecasts[index].conditionText,
                modifier = Modifier.size(64.dp)
            )
        }

    }
}


