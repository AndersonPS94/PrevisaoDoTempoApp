package com.example.previsaodotempo.activities.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
        enableEdgeToEdge()
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
        val date = calendar.get(Calendar.DATE)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault())
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, java.util.Locale.getDefault())


        return String.format("%s, %d %s", dayOfWeek, date, month)
    }
}
