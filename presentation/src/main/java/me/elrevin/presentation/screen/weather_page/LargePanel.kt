package me.elrevin.presentation.screen.weather_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import me.elrevin.presentation.base_ui.theme.AppTheme

@Composable
internal fun LargePanel(
    icon: ImageVector,
    title: String,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.panelBackground)
            .fillMaxWidth()
            .padding(6.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(50))
                    .background(AppTheme.colors.iconsBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = "",
                    tint = AppTheme.colors.iconsForeground
                )
            }

            Text(
                modifier = Modifier.padding(start = 34.dp),
                text = title,
                style = AppTheme.typography.Title,
                color = AppTheme.colors.panelText
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp),
            contentAlignment = Alignment.TopStart
        ) {
            content()
        }

    }
}