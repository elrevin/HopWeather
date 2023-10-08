package me.elrevin.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.elrevin.core.other.Constants
import me.elrevin.data.local.Dao
import me.elrevin.data.local.DataBase
import me.elrevin.data.remote.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context
    ): DataBase = Room
        .databaseBuilder(context, DataBase::class.java, "app_database")
        // TODO: After publishing migration mast be used, because not all data will be loaded every running
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: DataBase): Dao = db.getDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(Constants.apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =retrofit.create(WeatherApi::class.java)
}