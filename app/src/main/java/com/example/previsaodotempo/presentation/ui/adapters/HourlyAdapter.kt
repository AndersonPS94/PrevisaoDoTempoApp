package com.example.previsaodotempo.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.databinding.ItemListTodayBinding

class HourlyAdapter(private var timeList: List<String>, private var tempList: List<Double>)
    : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    inner class HourlyViewHolder(private val binding: ItemListTodayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(time: String, temp: Double) {
            binding.textTime.text = time
            binding.textTemperatura.text = temp.toString() + "°C"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListTodayBinding.inflate(inflater, parent, false)
        return HourlyViewHolder(binding)
    }

    override fun getItemCount(): Int = timeList.size

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(timeList[position], tempList[position])
    }


    fun updateData(newTimeList: List<String>, newTempList: List<Double>) {
        timeList = newTimeList
        tempList = newTempList
        notifyDataSetChanged()
    }
}
