package com.example.previsaodotempo.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.previsaodotempo.adapters.ViewPagerAdapter
import com.example.previsaodotempo.databinding.ActivityMainBinding
import com.example.previsaodotempo.fragments.NextDaysFragment
import com.example.previsaodotempo.fragments.TodayFragment
import com.example.previsaodotempo.fragments.TomorrowFragment
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

        binding.imageButton.setOnClickListener {
            intent = Intent(applicationContext, CityDetailsActivity::class.java)
            startActivity(intent)

            binding.btnToday.setOnClickListener {
                binding.ViewPagerPrincipal.currentItem = 0
                intent = Intent(applicationContext, TodayFragment::class.java)
                startActivity(intent)
            }

            binding.btnTomorrow.setOnClickListener {
                binding.ViewPagerPrincipal.currentItem = 1
                intent = Intent(applicationContext, TomorrowFragment::class.java)
                startActivity(intent)
            }

            binding.btnNextDays.setOnClickListener {
                binding.ViewPagerPrincipal.currentItem = 2
                intent = Intent(applicationContext, NextDaysFragment::class.java)
                startActivity(intent)
            }
        }
    }
}