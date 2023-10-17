package me.elrevin.presentation.screen.empty_page

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import me.elrevin.domain.model.Location

@Composable
fun EmptyPage(location: Location) {
    Text(text = "Empty page ${location.name}")
}