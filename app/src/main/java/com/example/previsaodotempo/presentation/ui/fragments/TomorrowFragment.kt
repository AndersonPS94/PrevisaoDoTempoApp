package com.example.previsaodotempo.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.databinding.FragmentTomorrowBinding
import com.example.previsaodotempo.presentation.ui.adapters.HourlyAdapter
import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TomorrowFragment : Fragment() {

    // Variáveis de ligação e ViewModel para a fragment de amanhã usando View Binding e Hilt.
    private lateinit var viewModel: WeatherViewModel
    private var _binding: FragmentTomorrowBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HourlyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTomorrowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializando o ViewModel e o adapter para a lista de previsões de amanhã.
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        adapter = HourlyAdapter(emptyList(), emptyList())
        binding.rvWeatherTomorrow.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvWeatherTomorrow.adapter = adapter

        // Observando os dados de previsão de amanhã e atualizando a lista no adapter.
        viewModel.tomorrowWeather.observe(viewLifecycleOwner) { hourly ->
            adapter.updateData(hourly.time, hourly.temperatures)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
