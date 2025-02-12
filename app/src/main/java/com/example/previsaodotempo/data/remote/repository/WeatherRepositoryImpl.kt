package com.example.previsaodotempo.data.remote.repository

import WeatherResponse
import com.example.previsaodotempo.data.remote.api.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
// Implementação da interface IWeatherRepository
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherService
) : IWeatherRepository {

    // Implementação da função para obter os dados do clima
    override suspend fun getWeatherData(lat: Double, lon: Double): WeatherResponse {
        return api.getWeatherData(lat, lon)
    }

}