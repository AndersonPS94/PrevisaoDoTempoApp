package com.example.previsaodotempo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.previsaodotempo.fragments.NextDaysFragment
import com.example.previsaodotempo.fragments.TodayFragment
import com.example.previsaodotempo.fragments.TomorrowFragment

class ViewPagerAdapter (
    val abas: List<String>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
)
    : FragmentStateAdapter(fragmentManager, lifecycle){
        override fun getItemCount() : Int {
            return abas.size
        }


    override fun createFragment(position: Int): Fragment {
         when (position) {
            0 -> return TodayFragment()
            1 -> return TomorrowFragment()
            2 -> return NextDaysFragment()
            }
        return TodayFragment()
        }
    }