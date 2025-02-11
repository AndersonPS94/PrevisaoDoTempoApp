package com.example.previsaodotempo.data.remote.repository

import com.example.previsaodotempo.data.remote.api.WeatherResponse

interface IWeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherResponse

}