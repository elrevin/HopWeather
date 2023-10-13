package me.elrevin.hopweather

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository
import me.elrevin.domain.repository.WeatherRepository
import me.elrevin.hopweather.ui.theme.HopWeatherTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var r: LocationRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var locationPermissionGranted by remember {
                mutableStateOf(false)
            }

            var deviceLocation: Location? by remember {
                mutableStateOf(null)
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted ->
                    if (isGranted) {
                        locationPermissionGranted = true
                    }
                }
            )

            LaunchedEffect(key1 = Unit, block = {
                launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            })
            
            HopWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (deviceLocation != null) {
                        Text(text = deviceLocation!!.name)
                    }
                    if (locationPermissionGranted) {
                        LaunchedEffect(key1 = Unit, block = {
                            val loc = r.getCurrentLocation()
                            if (loc.isSuccess()) {
                                deviceLocation = loc.getValue()
                            }
                        })
                    } else {
                        Text(text = "Not")
                    }

                }
            }
        }
    }
}