package me.elrevin.presentation.screen.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.elrevin.domain.model.Location
import me.elrevin.presentation.base_ui.theme.AppTheme
import me.elrevin.presentation.base_ui.theme.ColorWhite
import java.time.LocalDateTime

@Composable
fun LoadingPage(location: Location) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp)
                .clip(
                    AppTheme.shapes.large.copy(
                        topStart = CornerSize(0),
                        topEnd = CornerSize(0),
                    )
                ),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = if (LocalDateTime.now().hour in (6 .. 18)) "https://elrevin.me/hop-weather-images/unknown-day.jpg"
                else "https://elrevin.me/hop-weather-images/unknown-night.jpg",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomCenter
            )
            HeaderRow(
                location = location,
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
private fun HeaderRow(
    location: Location
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 58.dp, start = 16.dp, end = 16.dp),
        text = "${location.name}, ${location.country}",
        style = AppTheme.typography.H1,
        color = ColorWhite
    )
}