package me.elrevin.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEmpty
import me.elrevin.core.other.Constants
import me.elrevin.core.other.lowerOrNull
import me.elrevin.domain.model.CurrentWeather
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository,
    private val loadCurrentWeatherUseCase: LoadCurrentWeatherUseCase = LoadCurrentWeatherUseCase(repository)
) {
    operator fun invoke(location: Location): Flow<Either<CurrentWeather>> =
        repository.getCurrentWeather(location)
            .flatMapConcat {
                loadCurrentWeatherUseCase(location, it)
            }
            .distinctUntilChangedBy {
                when {
                    it.isSuccess() -> "${it.getValue().location.id} ${it.getValue().lastUpdatedTimestamp}"
                    it.isLoading() -> "loading"
                    it.isFailure() -> "failure"
                    else -> "exception"
                }
            }
            .flowOn(Dispatchers.IO)
}