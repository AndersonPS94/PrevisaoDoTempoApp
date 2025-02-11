import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _todayWeather = MutableLiveData<HourlyData>()
    val todayWeather: LiveData<HourlyData> get() = _todayWeather

    private val _tomorrowWeather = MutableLiveData<HourlyData>()
    val tomorrowWeather: LiveData<HourlyData> get() = _tomorrowWeather

    private val _weeklyWeather = MutableLiveData<DailyData>()
    val weeklyWeather: LiveData<DailyData> get() = _weeklyWeather

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherData(lat, lon)
                _weatherData.postValue(response)

                // Atualiza os dados para cada Fragment
                _todayWeather.postValue(filterTodayWeather(response.hourly))
                _tomorrowWeather.postValue(filterTomorrowWeather(response.hourly))
                _weeklyWeather.postValue(response.daily)

            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Erro ao buscar clima: ${e.message}")
            }
        }
    }

    private fun filterTodayWeather(hourlyData: HourlyData): HourlyData {
        return HourlyData(
            time = hourlyData.time.take(10), // Pegando apenas as próximas 10 horas
            temperatures = hourlyData.temperatures.take(10),
            windSpeeds = hourlyData.windSpeeds.take(10),
            humidityLevels = hourlyData.humidityLevels.take(10),
            precipitationProbability = hourlyData.precipitationProbability.take(10),
            weatherCodes = hourlyData.weatherCodes.take(10)
        )
    }

    private fun filterTomorrowWeather(hourlyData: HourlyData): HourlyData {
        return HourlyData(
            time = hourlyData.time.drop(24).take(24), // Pegando as 24 horas de amanhã
            temperatures = hourlyData.temperatures.drop(24).take(24),
            windSpeeds = hourlyData.windSpeeds.drop(24).take(24),
            humidityLevels = hourlyData.humidityLevels.drop(24).take(24),
            precipitationProbability = hourlyData.precipitationProbability.drop(24).take(24),
            weatherCodes = hourlyData.weatherCodes.drop(24).take(24)
        )
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
