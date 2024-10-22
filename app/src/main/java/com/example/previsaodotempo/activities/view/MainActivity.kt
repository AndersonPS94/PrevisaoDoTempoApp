package com.example.previsaodotempo.activities.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.previsaodotempo.adapters.ViewPagerAdapter
import com.example.previsaodotempo.databinding.ActivityMainBinding
import com.example.previsaodotempo.fragments.NextDaysFragment
import com.example.previsaodotempo.fragments.TodayFragment
import com.example.previsaodotempo.fragments.TomorrowFragment
import com.example.previsaodotempo.model.data.Hourly
import com.example.previsaodotempo.vielmodel.WeatherViewModel
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
        inicializandoNavegacaoAbas()
        RecuperandoDados()
    }

    private fun RecuperandoDados() {
        // Configurando o ViewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        //Observando o estado da previsao do tempo
        viewModel.todayWeather.observe(this) {
            // Atualizando a cidade, temperatura e hora
            binding.textNomeCidade.text = "São Paulo"  // Cidade fixa
            // Temperatura atualiza de acordo com a api
            binding.textTemperaturaAtual.text = "${it.temperature_2m[0]}º"
            // Atualizando o horário em tempo real
            binding.textDiaMesSemana.text = getCurrentTime()
        }
    }

    private fun inicializandoNavegacaoAbas() {
        val tabLayout = binding.tabLayoutInfo
        val viewPager = binding.ViewPagerPrincipal

        // Lista de títulos das abas
        val abas = listOf("Today", "Tomorrow", "Next 10 days")
        viewPager.adapter = ViewPagerAdapter(abas, supportFragmentManager, lifecycle)

        // Configurando o TabLayoutMediator para sincronizar com o ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = abas[position]
        }.attach()
    }
}
    // Função para pegar o horário local
    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }

