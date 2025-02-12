package com.example.previsaodotempo.di

import com.example.previsaodotempo.data.remote.api.WeatherService
import com.example.previsaodotempo.data.remote.repository.IWeatherRepository
import com.example.previsaodotempo.data.remote.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Módulo para a injeção de dependência usando Hilt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Fornece uma instância única do Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Fornece uma instância única do WeatherService
    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    // Fornece uma instância única do WeatherRepository
    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherService): IWeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}