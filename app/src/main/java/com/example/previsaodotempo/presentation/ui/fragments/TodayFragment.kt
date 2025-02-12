package com.example.previsaodotempo.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.databinding.FragmentTodayBinding
import com.example.previsaodotempo.presentation.ui.adapters.HourlyAdapter
import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment() {
    // Variáveis de ligação e ViewModel para o fragmento de hoje usando View Binding e Hilt.
    private lateinit var viewModel: WeatherViewModel
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HourlyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializando o ViewModel, o adapter e o RecyclerView.
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        adapter = HourlyAdapter(emptyList(), emptyList())
        binding.rvWeatherToday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvWeatherToday.adapter = adapter

        // Observando os dados do clima para hoje e atualizando o adapter.
        viewModel.todayWeather.observe(viewLifecycleOwner) { hourly ->
            adapter.updateData(hourly.time, hourly.temperatures)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
