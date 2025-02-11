package com.example.previsaodotempo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.previsaodotempo.data.remote.api.WeatherResponse
import com.example.previsaodotempo.data.remote.repository.IWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: IWeatherRepository
) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherData(lat, lon)
                _weatherData.postValue(response)
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Erro ao buscar clima: ${e.message}")
            }
        }
    }

    fun getWeatherDescription(code: Int): String {
        return when (code) {
            0 -> "Ensolarado"
            1, 2, 3 -> "Parcialmente Nublado"
            45, 48 -> "Neblina"
            51, 53, 55 -> "Garoa"
            61, 63, 65 -> "Chuva"
            71, 73, 75 -> "Neve"
            95, 96, 99 -> "Tempestade"
            else -> "Desconhecido"
        }
    }
}