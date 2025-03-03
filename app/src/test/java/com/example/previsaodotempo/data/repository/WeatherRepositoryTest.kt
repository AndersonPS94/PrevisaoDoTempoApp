package com.example.previsaodotempo.data.repository

import DailyData
import HourlyData
import WeatherResponse
import com.example.previsaodotempo.data.remote.api.WeatherService
import com.example.previsaodotempo.data.remote.repository.WeatherRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class WeatherRepositoryImplTest {

    private lateinit var repositorio: WeatherRepositoryImpl
    private lateinit var api: WeatherService

    @Before
    fun configurar() {
        api = mock(WeatherService::class.java)
        repositorio = WeatherRepositoryImpl(api)
    }

    @Test
    fun `deve retornar dados do clima quando a chamada da API for bem-sucedida`() = runBlocking {
        // Simulando uma resposta realista da API Open Meteo
        val latitude = -23.55
        val longitude = -46.63

        val respostaFalsa = WeatherResponse(
            hourly = HourlyData(
                time = listOf("2024-03-03T10:00", "2024-03-03T11:00"),
                temperatures = listOf(25.5, 26.2),
                windSpeeds = listOf(10.5, 12.0),
                humidityLevels = listOf(60.0, 55.0),
                precipitationProbability = listOf(20.0, 15.0),
                weatherCodes = listOf(1, 2)
            ),
            daily = DailyData(
                time = listOf("2024-03-03", "2024-03-04"),
                maxTemperatures = listOf(30.0, 28.5),
                minTemperatures = listOf(18.0, 19.2),
                weatherCodes = listOf(3, 2)
            )
        )

        // Configurando o mock para retornar a resposta falsa
        whenever(api.getWeatherData(latitude, longitude)).thenReturn(respostaFalsa)

        // Chamando a função do repositório
        val resultado = repositorio.getWeatherData(latitude, longitude)

        // Verificando se o retorno é o esperado
        assertEquals(respostaFalsa, resultado)
    }

    @Test
    fun `deve lançar exceção quando a chamada da API falhar` (): Unit = runBlocking {
        val latitude = -23.55
        val longitude = -46.63
        val excecao = mock(HttpException::class.java)

        // Configurando o mock para lançar uma exceção
        whenever(api.getWeatherData(latitude, longitude)).thenThrow(excecao)

        // Verificando se o repositório lança a exceção corretamente
        assertThrows(HttpException::class.java) {
            runBlocking { repositorio.getWeatherData(latitude, longitude) }
        }
    }
}
