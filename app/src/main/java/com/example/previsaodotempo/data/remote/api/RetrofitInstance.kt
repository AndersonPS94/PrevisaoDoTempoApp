package com.example.previsaodotempo.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Classe de instância do Retrofit para a API de previsão do tempo
object RetrofitInstance {
    val api: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}