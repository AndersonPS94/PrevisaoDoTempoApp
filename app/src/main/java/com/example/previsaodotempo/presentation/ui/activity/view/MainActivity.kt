package com.example.previsaodotempo.presentation.ui.activity.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.previsaodotempo.presentation.ui.adapters.ViewPagerAdapter
import com.example.previsaodotempo.databinding.ActivityMainBinding
import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando o ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        inicializarNavegacaoAbas()
        recuperarDados()
        iniciarNovaAct()

    }

    private fun iniciarNovaAct() {
        binding.textNomeCidade.setOnClickListener {
            intent = Intent(this, CityDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.btnCityDetails.setOnClickListener {
            intent = Intent(this, CityDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun recuperarDados() {
        viewModel.weatherData.observe(this) { weather ->
            if (weather == null || weather.hourly == null) {
                Log.e("MainActivity", "Dados do clima estão nulos!")
                return@observe
            }

            val temp = weather.hourly.temperatures?.firstOrNull() ?: 0.0
            val windSpeed = weather.hourly.windSpeeds?.firstOrNull() ?: 0.0
            val humidity = weather.hourly.humidityLevels?.firstOrNull() ?: 0.0
            val probability = weather.hourly.precipitationProbability?.firstOrNull() ?: 0.0
            val weatherCode = weather.hourly.weatherCodes?.firstOrNull() ?: 0
            val weatherDescription = viewModel.getWeatherDescription(weatherCode)
            val weatherIcon = viewModel.getWeatherIcon(weatherCode)

            // Atualiza a UI
            binding.textTemperaturaAtual.text = "$temp°"
            binding.textVelVento.text = "$windSpeed km/h"
            binding.textHumidade.text = "$humidity%"
            binding.textProbabilidadeChuva.text = "$probability%"
            binding.textDescTemp.text = weatherDescription
            binding.textNomeCidade.text = "São Paulo"
            binding.textDiaMesSemana.text = getCurrentTime()
            binding.imageTempo.setImageResource(weatherIcon)
        }

        // Buscar clima para São Paulo (-23.5505, -46.6333)
        viewModel.fetchWeather(-23.5505, -46.6333)
    }



    private fun inicializarNavegacaoAbas() {
        val tabLayout = binding.tabLayoutInfo
        val viewPager = binding.viewpagerPrincipal

        // Lista de títulos das abas
        val abas = listOf("Today", "Tomorrow", "Next 10 days")
        viewPager.adapter = ViewPagerAdapter(abas, supportFragmentManager, lifecycle)

        // Configurando o TabLayoutMediator para sincronizar com o ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = abas[position]
        }.attach()
    }

    // Função para pegar o horário local
    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DATE)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault())
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, java.util.Locale.getDefault())


        return String.format("%s, %d %s", dayOfWeek, date, month)
    }
}
