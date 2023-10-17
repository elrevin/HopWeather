package me.elrevin.presentation.screen.error_page

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import me.elrevin.domain.model.Location

@Composable
fun ErrorPage(location: Location, errorCode: String) {
    Text(text = "ERROR PAGE ${location.name} - $errorCode")
}