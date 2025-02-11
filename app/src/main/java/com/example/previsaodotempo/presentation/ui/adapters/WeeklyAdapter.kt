package com.example.previsaodotempo.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.databinding.ItemTodayNextdaysBinding

class WeeklyAdapter(
    private var dateList: List<String>,
    private var minTempList: List<Double>,
    private var maxTempList: List<Double>
) : RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder>() {

    inner class WeeklyViewHolder(
        private val binding: ItemTodayNextdaysBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(date: String, minTemp: Double, maxTemp: Double) {
            // Vincule os dados aos elementos de UI aqui
            binding.textDayWeek.text = date
            binding.textMin.text = "$minTemp°C"
            binding.textMax.text = "$maxTemp°C"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodayNextdaysBinding.inflate(inflater, parent, false)
        return WeeklyViewHolder(binding)
    }

    override fun getItemCount(): Int = dateList.size

    override fun onBindViewHolder(holder: WeeklyViewHolder, position: Int) {
        holder.bind(dateList[position], minTempList[position], maxTempList[position])
    }

    // Função para atualizar os dados do adaptador
    fun updateData(newDateList: List<String>, newMinTempList: List<Double>, newMaxTempList: List<Double>) {
        dateList = newDateList
        minTempList = newMinTempList
        maxTempList = newMaxTempList
    }
}
