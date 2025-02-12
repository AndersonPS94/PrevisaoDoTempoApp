package com.example.previsaodotempo.data.remote.repository

import WeatherResponse


// Interface que define o contrato para a implementação do repositório de clima
interface IWeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherResponse

}