package com.example.previsaodotempo.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.previsaodotempo.R
import com.example.previsaodotempo.adapters.NextDaysAdapter
import com.example.previsaodotempo.databinding.FragmentNextDaysBinding
import com.example.previsaodotempo.vielmodel.NextDaysViewModel

class NextDaysFragment : Fragment() {

    private lateinit var binding: FragmentNextDaysBinding
    //private lateinit var viewModel: NextDaysViewModel
    private lateinit var adapter: NextDaysAdapter

    companion object {
        fun newInstance() = NextDaysFragment()
    }

    private val viewModel: NextDaysViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_next_days, container, false)
    }
}