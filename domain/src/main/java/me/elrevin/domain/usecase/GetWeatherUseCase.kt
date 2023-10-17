package me.elrevin.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import me.elrevin.domain.model.Either
import me.elrevin.domain.model.Location
import me.elrevin.domain.model.Weather
import me.elrevin.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository,
    private val loadWeatherUseCase: LoadWeatherUseCase = LoadWeatherUseCase(repository)
) {
    operator fun invoke(location: Location): Flow<Either<Weather>> =
        repository.getWeather(location)
            .flatMapConcat {
                loadWeatherUseCase(location, it)
            }
            .distinctUntilChangedBy {
                when {
                    it.isSuccess() -> it.getValue().hashCode()
                    it.isLoading() -> "loading"
                    it.isFailure() -> "failure"
                    else -> "exception"
                }
            }
            .flowOn(Dispatchers.IO)
}
