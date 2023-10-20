package me.elrevin.presentation.screen.error_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.elrevin.core.other.Constants
import me.elrevin.domain.model.Location
import me.elrevin.presentation.base_ui.theme.AppTheme

@Composable
fun ErrorPage(location: Location, errorCode: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp)
                .clip(
                    AppTheme.shapes.large.copy(
                        topStart = CornerSize(0),
                        topEnd = CornerSize(0),
                    )
                ),
            model = "https://elrevin.me/hop-weather-images/error.jpg",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomCenter
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = Constants.Errors.getErrorByCode("apiError1005"),
                style = AppTheme.typography.TinyHead,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.panelText
            )
        }
    }
}