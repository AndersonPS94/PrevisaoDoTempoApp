package com.example.previsaodotempo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.previsaodotempo.R
import com.example.previsaodotempo.databinding.ActivityCityDetailsBinding

class CityDetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCityDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        eventoClique()


    }

    private fun eventoClique() {
        binding.btnVoltar.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}