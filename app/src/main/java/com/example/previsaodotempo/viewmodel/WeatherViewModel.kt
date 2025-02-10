package com.example.previsaodotempo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.previsaodotempo.api.RetrofitInstance
import com.example.previsaodotempo.model.data.Daily
import com.example.previsaodotempo.model.data.Hourly
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _todayWeather = MutableLiveData<Hourly>()
    val todayWeather: LiveData<Hourly> = _todayWeather

    private val _tomorrowWeather = MutableLiveData<Hourly>()
    val tomorrowWeather: LiveData<Hourly> = _tomorrowWeather

    private val _weeklyWeather = MutableLiveData<Daily>()
    val weeklyWeather: LiveData<Daily> = _weeklyWeather

    // Função para buscar dados da API
    fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeatherData(
                    latitude = -23.5505,
                    longitude = -46.6333,
                    hourly = "temperature_2m",
                    daily = "temperature_2m_min,temperature_2m_max",
                    timezone = "America/Sao_Paulo"


                )

                // Atualizando os dados para hoje, amanhã e os próximos 10 dias
                _todayWeather.value = Hourly(
                    time = response.hourly.time.take(24),  // Pega as primeiras 24 horas
                    temperature_2m = response.hourly.temperature_2m.take(24),

                )

                _tomorrowWeather.value = Hourly(
                    time = response.hourly.time.drop(24).take(24),  // Pega as próximas 24 horas
                    temperature_2m = response.hourly.temperature_2m.drop(24).take(24),

                )

                _weeklyWeather.value = response.daily

            } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
