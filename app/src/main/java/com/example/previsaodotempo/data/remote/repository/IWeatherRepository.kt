package com.example.previsaodotempo.data.remote.repository

import WeatherResponse

interface IWeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherResponse

}