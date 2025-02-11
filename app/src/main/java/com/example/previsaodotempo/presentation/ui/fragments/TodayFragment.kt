package com.example.previsaodotempo.fragments

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

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentTodayBinding
    private lateinit var adapter: HourlyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HourlyAdapter(emptyList(), emptyList())

        binding.rvWeatherToday.adapter = adapter

        binding.rvWeatherToday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = this@TodayFragment.adapter

        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)



        viewModel.todayWeather.observe(viewLifecycleOwner) { weather ->
            adapter.updateData(weather.time, weather.temperature_2m)
        }

        viewModel.fetchWeatherData( 52.5200, 13.4050)
    }
}}