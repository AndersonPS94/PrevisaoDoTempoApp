package com.example.previsaodotempo.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.previsaodotempo.R
import com.example.previsaodotempo.vielmodel.TomorrowViewModel

class TomorrowFragment : Fragment() {

    companion object {
        fun newInstance() = TomorrowFragment()
    }

    private val viewModel: TomorrowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tomorrow, container, false)
    }
}