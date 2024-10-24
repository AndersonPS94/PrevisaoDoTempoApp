package com.example.previsaodotempo.model.data

// Modelo de dados baseado na resposta da API

data class WeatherResponse(
    val hourly: Hourly,
    val daily: Daily
)

data class Hourly(
    val time: List<String>,
    val temperature_2m: List<Double>
)



data class Daily(
    val time: List<String>,
    val temperature_2m_min: List<Double>,
    val temperature_2m_max: List<Double>
)
