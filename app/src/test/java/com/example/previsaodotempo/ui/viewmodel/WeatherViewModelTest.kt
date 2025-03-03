package com.example.previsaodotempo.ui.viewmodel

import DailyData
import HourlyData
import WeatherResponse
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.previsaodotempo.R
import com.example.previsaodotempo.data.remote.repository.IWeatherRepository
import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    // Regra para execução síncrona de LiveData
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherRepository: IWeatherRepository

    // Configuração do dispatcher para testes de corrotinas
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher) // Define o dispatcher de testes
        weatherRepository = mock() // Cria um mock do repositório
        weatherViewModel = WeatherViewModel(weatherRepository) // Instancia o ViewModel
    }

    @Test
    fun `test fetchWeather success`() = runTest {
        // Simulação da resposta do repositório
        val weatherResponse = WeatherResponse(
            hourly = HourlyData(
                time = listOf("10:00", "11:00"), // List<String>
                temperatures = listOf(25.0, 26.0),
                windSpeeds = listOf(5.0, 6.0),
                humidityLevels = listOf(80.0, 85.0),
                precipitationProbability = listOf(10.0, 20.0),
                weatherCodes = listOf(0, 1) // List<Int>
            ),
            daily = DailyData(
                time = listOf("2025-03-01", "2025-03-02"), // List<String>
                minTemperatures = listOf(22.0, 24.0),
                maxTemperatures = listOf(28.0, 30.0),
                weatherCodes = listOf(0, 1) // List<Int>
            )
        )

        // Configurar o mock para retornar a resposta esperada
        whenever(weatherRepository.getWeatherData(any(), any())).thenReturn(weatherResponse)

        // Criar um observer mockado para capturar mudanças no LiveData
        val observer = mock<Observer<WeatherResponse>>()
        weatherViewModel.weatherData.observeForever(observer)

        // Chamar a função de buscar o clima
        weatherViewModel.fetchWeather(0.0, 0.0)

        // Avançar até que todas as corrotinas sejam executadas
        dispatcher.scheduler.advanceUntilIdle()

        // Verificar se o LiveData foi atualizado corretamente
        verify(observer).onChanged(weatherResponse)
    }

    @Test
    fun `test getWeatherDescription`() {
        // Teste para verificar se as descrições estão corretas
        val sunnyDescription = weatherViewModel.getWeatherDescription(0)
        val unknownDescription = weatherViewModel.getWeatherDescription(999)

        assert(sunnyDescription == "Sunny")
        assert(unknownDescription == "Unknown")
    }

    @Test
    fun `test getWeatherIcon`() {
        // Teste para verificar se os ícones retornados estão corretos
        val sunnyIcon = weatherViewModel.getWeatherIcon(0)
        val unknownIcon = weatherViewModel.getWeatherIcon(999)

        assert(sunnyIcon == R.drawable.ensolarado)
        assert(unknownIcon == R.drawable.desconhecido)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Resetar o dispatcher após os testes
    }
}
