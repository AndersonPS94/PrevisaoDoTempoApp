package com.example.previsaodotempo.presentation.ui.fragments

import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.databinding.FragmentNextDaysBinding
import com.example.previsaodotempo.presentation.ui.adapters.WeeklyAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NextDaysFragment : Fragment() {
    // Variáveis de ligação e ViewModel
    private lateinit var viewModel: WeatherViewModel
    private var _binding: FragmentNextDaysBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WeeklyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNextDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializando o ViewModel e o Adapter
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        adapter = WeeklyAdapter(emptyList(), emptyList(), emptyList())
        binding.rvWeekly.layoutManager = LinearLayoutManager(context)
        binding.rvWeekly.adapter = adapter

        // Observando os dados do clima para os próximos dias e atualizando o adapter
        viewModel.weeklyWeather.observe(viewLifecycleOwner) { weather ->
            adapter.updateData(weather.time, weather.minTemperatures, weather.maxTemperatures)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
