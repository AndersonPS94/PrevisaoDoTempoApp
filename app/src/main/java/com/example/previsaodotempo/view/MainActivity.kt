package com.example.previsaodotempo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.previsaodotempo.R
import com.example.previsaodotempo.adapters.ViewPagerAdapter
import com.example.previsaodotempo.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        eventoClique()
        inicializarNavegaçaoAbas()


    }

    private fun inicializarNavegaçaoAbas() {
        val tabLayout = binding.tabLayoutInfo
        val viewPager = binding.ViewPagerPrincipal

        val abas = listOf("Today", "Tomorrow", "Next 10 days")
        viewPager.adapter = ViewPagerAdapter(abas, supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = abas[position]
        }.attach()
    }

    private fun eventoClique() {
        binding.textNomeCidade.setOnClickListener {
            intent = Intent(applicationContext, CityDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}