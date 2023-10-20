package me.elrevin.presentation.base_ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import me.elrevin.presentation.R

object Icons {
    @Composable fun check() = ImageVector.vectorResource(id = R.drawable.ic_check)
    @Composable fun pressure() = ImageVector.vectorResource(id = R.drawable.ic_pressure)
    @Composable fun rainy() = ImageVector.vectorResource(id = R.drawable.ic_rainy)
    @Composable fun wind() = ImageVector.vectorResource(id = R.drawable.ic_wind)
    @Composable fun sunrise() = ImageVector.vectorResource(id = R.drawable.ic_sunrise)
    @Composable fun sunset() = ImageVector.vectorResource(id = R.drawable.ic_sunset)
    @Composable fun uvIndex() = ImageVector.vectorResource(id = R.drawable.ic_uv_index)
    @Composable fun watch() = ImageVector.vectorResource(id = R.drawable.ic_watch)
    @Composable fun dayTemperature() = ImageVector.vectorResource(id = R.drawable.ic_day_temperature)
    @Composable fun nightTemperature() = ImageVector.vectorResource(id = R.drawable.ic_night_temperature)
}