package com.example.previsaodotempo.data.remote.repository

import com.example.previsaodotempo.domain.model.WeatherResponse
import com.example.previsaodotempo.data.remote.api.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherService
) : IWeatherRepository {

    override suspend fun getWeatherData(lat: Double, lon: Double): WeatherResponse {
        return api.getWeatherData(lat, lon)
    }

}