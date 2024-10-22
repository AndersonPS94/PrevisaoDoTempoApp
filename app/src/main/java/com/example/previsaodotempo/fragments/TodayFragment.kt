package com.example.previsaodotempo.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.previsaodotempo.R
import com.example.previsaodotempo.adapters.HourlyAdapter
import com.example.previsaodotempo.databinding.FragmentTodayBinding
import com.example.previsaodotempo.vielmodel.WeatherViewModel

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

        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

      /*  adapter = HourlyAdapter(emptyList())
        binding.rvItemToday.adapter = adapter*/

        viewModel.todayWeather.observe(viewLifecycleOwner) { weather ->
            adapter.updateData(weather.time, weather.temperature_2m)
        }

        viewModel.fetchWeatherData()
    }
}
