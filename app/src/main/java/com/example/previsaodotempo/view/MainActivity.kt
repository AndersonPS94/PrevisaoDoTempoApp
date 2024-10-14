package com.example.previsaodotempo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.previsaodotempo.R
import com.example.previsaodotempo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        eventoClique()



    }

    private fun eventoClique() {
        binding.textNomeCidade.setOnClickListener {
            intent = Intent(applicationContext, CityDetailsActivity::class.java)
            startActivity(intent)
        }


    }
}