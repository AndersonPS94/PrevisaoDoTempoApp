package com.example.previsaodotempo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.adapters.HourlyAdapter
import com.example.previsaodotempo.databinding.FragmentTomorrowBinding
import com.example.previsaodotempo.viewmodel.WeatherViewModel
import java.util.Calendar

class TomorrowFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentTomorrowBinding
    private lateinit var adapter: HourlyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTomorrowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HourlyAdapter(emptyList(), emptyList())

        binding.rvWeatherTomorrow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@TomorrowFragment.adapter
        }

        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        binding.rvWeatherTomorrow.adapter = adapter
        viewModel.tomorrowWeather.observe(viewLifecycleOwner) { weather ->
            adapter.updateData(weather.time, weather.temperature_2m)
        }

    }


}

