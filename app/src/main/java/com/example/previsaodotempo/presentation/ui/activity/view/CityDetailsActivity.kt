package com.example.previsaodotempo.presentation.ui.activity.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.presentation.ui.adapters.WeeklyAdapter
import com.example.previsaodotempo.databinding.ActivityCityDetailsBinding
import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
//
class CityDetailsActivity : AppCompatActivity() {

    // Variáveis de ligação e ViewModel para a atividade de detalhes da cidade usando View Binding e Hilt.
    private lateinit var binding: ActivityCityDetailsBinding
    private lateinit var adapter: WeeklyAdapter
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()

        eventoClique()


        configurarListaProxDias()
        recuperarCidadeDados()
    }

    // Configurando o RecyclerView para exibir os dados dos próximos dias
    private fun configurarListaProxDias() {
        // Inicializando o adapter com listas vazias
        adapter = WeeklyAdapter()
        binding.rvNextDays.adapter = adapter
        binding.rvNextDays.layoutManager = LinearLayoutManager(this)

        // Observando os dados do clima para os próximos dias
        viewModel.weatherData.observe(this) { weather ->
            if (weather == null || weather.daily == null) {
                Log.e("CityDetailsActivity", "Dados do clima para os próximos dias estão nulos!")
                return@observe
            }

            // Pegando as listas de data, temperaturas mínimas e máximas
            val dates = weather.daily.time ?: emptyList()
            val minTemps = weather.daily.minTemperatures ?: emptyList()
            val maxTemps = weather.daily.maxTemperatures ?: emptyList()

            // Atualizando o adapter com os dados
            adapter.updateData(dates, minTemps, maxTemps)
        }
    }



    // Recuperando dados do clima para a cidade de São Paulo
    private fun recuperarCidadeDados() {
        viewModel.weatherData.observe(this) { weather ->
            if (weather == null || weather.daily == null) {
                Log.e("CityDetailsActivity", "Dados do clima estão nulos!")
                return@observe
            }

            // Pegando os dados de temperatura, velocidade do vento, humidade e probabilidade de chuva
            val temp = weather.hourly?.temperatures?.firstOrNull() ?: 0.0
            val windSpeed = weather.hourly?.windSpeeds?.firstOrNull() ?: 0.0
            val humidity = weather.hourly?.humidityLevels?.firstOrNull() ?: 0.0
            val probability = weather.hourly?.precipitationProbability?.firstOrNull() ?: 0.0
            val weatherCode = weather.hourly?.weatherCodes?.firstOrNull() ?: 0
            val weatherDescription = viewModel.getWeatherDescription(weatherCode)

            // Atualizando a UI com os dados
            binding.textTempAtual.text = "$temp°C"
            binding.textVelVento.text = "$windSpeed km/h"
            binding.textHumidade.text = "$humidity%"
            binding.textProbabilidadeChuva.text = "$probability%"
            binding.textCondClimatica.text = "$weatherDescription"
            binding.textNomeCidade2.text = "São Paulo"

        }

        viewModel.fetchWeather(-23.5505, -46.6333)
    }

    private fun eventoClique() {
        binding.btnVoltar.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}