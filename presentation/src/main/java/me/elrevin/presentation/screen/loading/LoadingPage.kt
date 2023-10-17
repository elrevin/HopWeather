package me.elrevin.presentation.screen.loading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import me.elrevin.domain.model.Location

@Composable
fun LoadingPage(location: Location) {
    Text(text = "LoadingPage ${location.name}")
}