package com.example.previsaodotempo.data.remote.repository

import com.example.previsaodotempo.domain.model.WeatherResponse

interface IWeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherResponse

}