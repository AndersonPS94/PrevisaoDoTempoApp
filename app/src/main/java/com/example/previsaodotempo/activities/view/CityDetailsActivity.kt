package com.example.previsaodotempo.activities.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.adapters.WeeklyAdapter
import com.example.previsaodotempo.databinding.ActivityCityDetailsBinding
import com.example.previsaodotempo.viewmodel.WeatherViewModel


class CityDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivityCityDetailsBinding
    private lateinit var adapter: WeeklyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(binding.root)
        eventoClique()
        recuperarDados()
        inicializarLista()

    }

    private fun inicializarLista() {

        adapter = WeeklyAdapter(emptyList(), emptyList(), emptyList())

        binding.rvNextDays.adapter = adapter
        binding.rvNextDays.layoutManager = LinearLayoutManager(applicationContext)

        adapter = this@CityDetailsActivity.adapter


        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.weeklyWeather.observe(this) { weather ->
            adapter.updateData(weather.time, weather.temperature_2m_min, weather.temperature_2m_max)
        }
    }

    private fun recuperarDados() {
        // Configurando o ViewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        // Observando o estado da previsão do tempo
        viewModel.todayWeather.observe(this) {
            // Atualizando a cidade, temperatura e hora
            binding.textNomeCidade2.text = "São Paulo"  // Cidade fixa
            // Temperatura atualizada de acordo com a API
            binding.textTempAtual.text = "${it.temperature_2m[0]}º"

        }
    }

    private fun eventoClique() {
        binding.btnVoltar.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}