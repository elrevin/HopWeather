package me.elrevin.domain.usecase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.elrevin.domain.repository.LocationRepository
import me.elrevin.domain.repository.WeatherRepository
import me.elrevin.domain.usecase.GetCurrentLocationUseCase
import me.elrevin.domain.usecase.GetLocationsUseCase
import me.elrevin.domain.usecase.GetWeatherUseCase
import me.elrevin.domain.usecase.LoadLocationByNameUseCase
import me.elrevin.domain.usecase.RemoveLocationUseCase
import me.elrevin.domain.usecase.SaveLocationUseCase
import me.elrevin.domain.usecase.SearchLocationUseCase

@Module
@InstallIn(ViewModelComponent::class)
object Module {
    @Provides
    @ViewModelScoped
    fun provideGetCurrentLocationUseCase(
        repository: LocationRepository
    ) = GetCurrentLocationUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSaveLocationUseCase(
        repository: LocationRepository
    ) = SaveLocationUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideLoadLocationByNameUseCase(
        repository: LocationRepository
    ) = LoadLocationByNameUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetLocationsUseCase(
        repository: LocationRepository
    ) = GetLocationsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetCurrentWeatherUseCase(
        repository: WeatherRepository
    ) = GetWeatherUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSearchLocationUseCase(
        repository: LocationRepository,
        loadLocationByNameUseCase: LoadLocationByNameUseCase
    ) = SearchLocationUseCase(repository, loadLocationByNameUseCase)

    @Provides
    @ViewModelScoped
    fun provideRemoveLocationUseCase(
        repository: LocationRepository
    ) = RemoveLocationUseCase(repository)
}