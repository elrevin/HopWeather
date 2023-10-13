package me.elrevin.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import me.elrevin.core.other.Constants
import me.elrevin.data.local.Dao
import me.elrevin.data.mapper.toDataEntity
import me.elrevin.data.mapper.toDomainModel
import me.elrevin.data.remote.LocationRemoteSource
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val dao: Dao,
    private val remoteSource: LocationRemoteSource,
    private val fusedLocationProviderClient: FusedLocationProviderClient
): LocationRepository {
    override suspend fun searchLocation(locationName: String): Either<List<Location>> {
        val result = remoteSource.searchLocation(locationName)
        if (result.isSuccess()) {
            return Either.success(result.getValue().map { it.toDomainModel() })
        }
        return Either.fromEither(result)
    }

    override suspend fun saveLocation(location: Location) {
        dao.upsertLocation(location.toDataEntity())
    }

    @RequiresPermission(
        anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"]
    )
    override suspend fun getCurrentLocation(): Either<Location> {
        // Get current location of device
        val locationOfDevice =fusedLocationProviderClient.lastLocation
        var result: Location? = null
        callbackFlow<android.location.Location?> {
            locationOfDevice.addOnSuccessListener { l ->
                trySend(l)
                close()
            }
            locationOfDevice.addOnFailureListener {
                close(it)
            }
            locationOfDevice.addOnCanceledListener {
                close()
            }
            awaitClose {}
        }.catch {
            Log.e("HopWeatherTag", it.message ?: "")
        }.collect {
            if (it != null) {
                val locationQuery = "${it.latitude},${it.longitude}"
                val res = searchLocation(locationQuery)
                if (res.isSuccess() && res.getValue().isNotEmpty()) {
                    result = res.getValue()[0]
                }
            }
        }

        if (result != null) {
            return Either.success(result!!)
        }

        return Either.failure(Constants.Errors.deviceCurrentLocation)
    }

}