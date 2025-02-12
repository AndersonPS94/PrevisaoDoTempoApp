package com.example.previsaodotempo.data.remote.api

import WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Interface para a API de previsão do tempo
interface WeatherService {
    @GET("forecast")
    suspend fun getWeatherData(
    @Query("latitude") lat: Double,
    @Query("longitude") lon: Double,
    @Query("hourly") hourly: String = "temperature_2m,wind_speed_10m,relative_humidity_2m,precipitation_probability,weather_code",
    @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
    @Query("timezone") timezone: String = "America/Sao_Paulo"
): WeatherResponse
}