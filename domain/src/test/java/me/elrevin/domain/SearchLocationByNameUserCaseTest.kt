package me.elrevin.domain

import kotlinx.coroutines.runBlocking
import me.elrevin.core.other.Constants
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.LocationRepository
import org.junit.Assert
import org.junit.Test

class SearchLocationByNameUserCaseTest {
    private val repository = FakeLocationRepository()

    @Test
    fun `Successfully searching location by name`() {
        runBlocking {
            val result = repository.searchLocation("Berl")
            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.getValue().size == 3)
        }
    }

    @Test
    fun `Successfully searching location by name with empty result`() {
        runBlocking {
            val result = repository.searchLocation("Neverland")
            Assert.assertTrue(result.isSuccess())
            Assert.assertTrue(result.getValue().size == 0)
        }
    }

    @Test
    fun `Error`() {
        runBlocking {
            val result = repository.searchLocation("Other")
            Assert.assertTrue(result.isFailure())
            Assert.assertEquals(result.getFailureMsgOrNull(), Constants.Errors.network)
        }
    }
}

private class FakeLocationRepository: LocationRepository {
    override suspend fun searchLocation(locationName: String): Either<List<Location>> {
        if (locationName == "Berl") {
            return Either.success(
                listOf(
                    Location("loc1"),
                    Location("loc2"),
                    Location("loc3"),
                )
            )
        }
        if (locationName == "Neverland") {
            return Either.success(
                listOf()
            )
        }

        return Either.failure(Constants.Errors.network)
    }

    override suspend fun saveLocation(location: Location) {

    }

}