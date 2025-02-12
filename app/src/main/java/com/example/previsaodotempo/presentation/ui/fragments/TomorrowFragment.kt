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

        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        adapter = HourlyAdapter(emptyList(), emptyList())
        binding.rvWeatherTomorrow.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvWeatherTomorrow.adapter = adapter

        viewModel.tomorrowWeather.observe(viewLifecycleOwner) { hourly ->
            adapter.updateData(hourly.time, hourly.temperatures)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
