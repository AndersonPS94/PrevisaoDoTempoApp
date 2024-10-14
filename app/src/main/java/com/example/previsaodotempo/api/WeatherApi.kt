package com.example.previsaodotempo.api

import com.example.previsaodotempo.model.data.WeatherAPIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String
    ): WeatherAPIResponse
}