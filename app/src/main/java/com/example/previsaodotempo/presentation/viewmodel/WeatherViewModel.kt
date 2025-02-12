package com.example.previsaodotempo.presentation.viewmodel

import DailyData
import HourlyData
import WeatherResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.previsaodotempo.R
import com.example.previsaodotempo.data.remote.repository.IWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


// Classe de ViewModel para a tela principal
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: IWeatherRepository
) : ViewModel() {

    // LiveData para os dados do clima
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _todayWeather = MutableLiveData<HourlyData>()
    val todayWeather: LiveData<HourlyData> get() = _todayWeather

    private val _tomorrowWeather = MutableLiveData<HourlyData>()
    val tomorrowWeather: LiveData<HourlyData> get() = _tomorrowWeather

    private val _weeklyWeather = MutableLiveData<DailyData>()
    val weeklyWeather: LiveData<DailyData> get() = _weeklyWeather

    // Função para buscar os dados do clima
    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherData(lat, lon)
                _weatherData.postValue(response)

                response.hourly?.let {
                    _todayWeather.postValue(filterTodayWeather(it))
                    _tomorrowWeather.postValue(filterTomorrowWeather(it))
                }

                response.daily?.let {
                    _weeklyWeather.postValue(it)
                }

            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Erro ao buscar clima: ${e.message}")
            }
        }
    }

    // Função para filtrar os dados para hoje
    private fun filterTodayWeather(hourlyData: HourlyData): HourlyData {
        return HourlyData(
            time = hourlyData.time?.take(10) ?: emptyList(),
            temperatures = hourlyData.temperatures?.take(10) ?: emptyList(),
            windSpeeds = hourlyData.windSpeeds?.take(10) ?: emptyList(),
            humidityLevels = hourlyData.humidityLevels?.take(10) ?: emptyList(),
            precipitationProbability = hourlyData.precipitationProbability?.take(10) ?: emptyList(),
            weatherCodes = hourlyData.weatherCodes?.take(10) ?: emptyList()
        )
    }

    // Função para filtrar os dados para o dia seguinte
    private fun filterTomorrowWeather(hourlyData: HourlyData): HourlyData {
        return HourlyData(
            time = hourlyData.time?.drop(24)?.take(24) ?: emptyList(),
            temperatures = hourlyData.temperatures?.drop(24)?.take(24) ?: emptyList(),
            windSpeeds = hourlyData.windSpeeds?.drop(24)?.take(24) ?: emptyList(),
            humidityLevels = hourlyData.humidityLevels?.drop(24)?.take(24) ?: emptyList(),
            precipitationProbability = hourlyData.precipitationProbability?.drop(24)?.take(24) ?: emptyList(),
            weatherCodes = hourlyData.weatherCodes?.drop(24)?.take(24) ?: emptyList()
        )
    }
    // Função para obter a descrição do clima com base no código
    fun getWeatherDescription(code: Int,): String {
        return when (code) {
            0 -> "Sunny"
            1, 2, 3 -> "Partly Cloudy"
            45,48 -> "Fog"
            51, 53, 55 -> "Drizzle"
            61, 63, 65 -> "Rain"
            71, 73, 75 -> "Snow"
            95, 96,99 -> "Thunderstorm"
            else -> "Unknown"
        }
    }

    // Função para obter o ícone de acordo com o código do clima
    fun getWeatherIcon(code: Int): Int {
        return when (code) {
            0 -> R.drawable.ensolarado // Ícone para "Ensolarado"
            1, 2, 3 -> R.drawable.nebuloso // Ícone para "Parcialmente Nublado"
            45, 48 -> R.drawable.nebuloso // Ícone para "Neblina"
            51, 53, 55 -> R.drawable.cloudy // Ícone para "Garoa"
            61, 63, 65 -> R.drawable.chuva // Ícone para "Chuva"
            71, 73, 75 -> R.drawable.snow // Ícone para "Neve"
            95, 96, 99 -> R.drawable.tempestade // Ícone para "Tempestade"
            else -> R.drawable.desconhecido // Ícone para "Desconhecido"
        }
    }
}
