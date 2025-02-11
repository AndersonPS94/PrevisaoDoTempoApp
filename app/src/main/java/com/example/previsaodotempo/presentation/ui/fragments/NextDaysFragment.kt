package com.example.previsaodotempo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.previsaodotempo.databinding.FragmentNextDaysBinding
import com.example.previsaodotempo.presentation.ui.adapters.WeeklyAdapter
import com.example.previsaodotempo.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NextDaysFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentNextDaysBinding
    private lateinit var adapter: WeeklyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WeeklyAdapter(emptyList(), emptyList(), emptyList())

        binding.rvWeekly.adapter = adapter
        binding.rvWeekly.layoutManager = LinearLayoutManager(context)

        adapter = this@NextDaysFragment.adapter


        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        viewModel.weeklyWeather.observe(viewLifecycleOwner) { weather ->
            adapter.updateData(weather.time, weather.temperature_2m_min, weather.temperature_2m_max)
        }
    }
}