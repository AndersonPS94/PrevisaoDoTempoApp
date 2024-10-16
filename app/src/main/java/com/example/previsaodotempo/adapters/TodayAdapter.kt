package com.example.previsaodotempo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.databinding.ItemListTodayBinding
import com.example.previsaodotempo.model.data.HourlyUnits


class TodayAdapter (
    val list: List<HourlyUnits>
    ): RecyclerView.Adapter<TodayAdapter.TodayViewHolder>() {

    inner class TodayViewHolder(
        private val binding: ItemListTodayBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HourlyUnits) {
            binding.textTime.text = item.time
            //binding.rvItemToday = item.temperature_2m
            //binding.imageWeather

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListTodayBinding.inflate(inflater, parent, false)
        return TodayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TodayViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }
}