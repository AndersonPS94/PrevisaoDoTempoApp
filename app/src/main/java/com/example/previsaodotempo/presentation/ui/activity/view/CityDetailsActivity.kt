package com.example.previsaodotempo.presentation.ui.activity.view

import WeatherViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.presentation.ui.adapters.WeeklyAdapter
import com.example.previsaodotempo.databinding.ActivityCityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityDetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCityDetailsBinding
    private lateinit var adapter: WeeklyAdapter
    private val viewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        eventoClique()
        enableEdgeToEdge()

        recuperarCidadeDados()
    }

    private fun recuperarCidadeDados() {
        // Observar os dados do ViewModel e atualizar a UI
        viewModel.weatherData.observe(this) { weather ->
            val temp = weather.hourly.temperatures[0]
            val windSpeed = weather.hourly.windSpeeds[0]
            val humidity = weather.hourly.humidityLevels[0]
            //val precipitation = weather.hourly.precipitationProbability[0]
            val weatherCode = weather.hourly.weatherCodes[0]
            val weatherDescription = viewModel.getWeatherDescription(weatherCode)

            // Atualizar a UI com os dados
            binding.textTempAtual.text = "$temp°C"
            binding.textVelVento.text = "$windSpeed km/h"
            binding.textHumidade.text = "$humidity%"
            //binding.tvPrecipitation.text = "Chuva: $precipitation%"
            binding.textCondClimatica.text = "$weatherDescription"
            binding.textNomeCidade2.text = "São Paulo"
        }

        // Buscar clima para São Paulo (-23.5505, -46.6333)
        viewModel.fetchWeather(-23.5505, -46.6333)
    }


    private fun eventoClique() {
        binding.btnVoltar.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

}