package com.example.previsaodotempo.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.previsaodotempo.R
import com.example.previsaodotempo.adapters.TodayAdapter
import com.example.previsaodotempo.databinding.FragmentTodayBinding
import com.example.previsaodotempo.vielmodel.TodayViewModel

class TodayFragment : Fragment() {
    private lateinit var binding: FragmentTodayBinding
    private lateinit var adapter: TodayAdapter
    //private lateinit var viewModel: TodayViewModel

    companion object {
        fun newInstance() = TodayFragment()
    }

    private val viewModel: TodayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }
}