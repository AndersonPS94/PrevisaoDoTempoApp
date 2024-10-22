package com.example.previsaodotempo.activities.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.previsaodotempo.adapters.ViewPagerAdapter
import com.example.previsaodotempo.databinding.ActivityMainBinding
import com.example.previsaodotempo.viewmodel.WeatherViewModel
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando o ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializarNavegacaoAbas()
        recuperarDados()
    }

    private fun recuperarDados() {  // Nome ajustado para camelCase
        // Configurando o ViewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        // Observando o estado da previsão do tempo
        viewModel.todayWeather.observe(this) {
            // Atualizando a cidade, temperatura e hora
            binding.textNomeCidade.text = "São Paulo"  // Cidade fixa
            // Temperatura atualizada de acordo com a API
            binding.textTemperaturaAtual.text = "${it.temperature_2m[0]}º"
            // Atualizando o horário em tempo real
            binding.textDiaMesSemana.text = getCurrentTime()
        }
    }

    private fun inicializarNavegacaoAbas() {  // Nome ajustado para camelCase
        val tabLayout = binding.tabLayoutInfo
        val viewPager = binding.viewpagerPrincipal // camelCase

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
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }
}
