package com.example.previsaodotempo.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.databinding.ItemTodayNextdaysBinding

class WeeklyAdapter(
    private var dateList: List<String> = emptyList(),
    private var minTempList: List<Double> = emptyList(),
    private var maxTempList: List<Double> = emptyList()
) : RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder>() {

    inner class WeeklyViewHolder(private val binding: ItemTodayNextdaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(date: String?, minTemp: Double?, maxTemp: Double?) {
            binding.textDayWeek.text = date ?: "-" // Evita crash se for nulo
            binding.textMin.text = minTemp?.let { "$it°C" } ?: "N/A°C"
            binding.textMax.text = maxTemp?.let { "$it°C" } ?: "N/A°C"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodayNextdaysBinding.inflate(inflater, parent, false)
        return WeeklyViewHolder(binding)
    }

    override fun getItemCount(): Int =
        dateList.size.coerceAtMost(minTempList.size).coerceAtMost(maxTempList.size)

    override fun onBindViewHolder(holder: WeeklyViewHolder, position: Int) {
        if (position < dateList.size && position < minTempList.size && position < maxTempList.size) {
            holder.bind(dateList[position], minTempList[position], maxTempList[position])
        } else {
            holder.bind("-", null, null)
        }
    }

    fun updateData(newDateList: List<String>?, newMinTempList: List<Double>?, newMaxTempList: List<Double>?) {
        // Atualizando os dados no adapter
        dateList = newDateList ?: emptyList()
        minTempList = newMinTempList ?: emptyList()
        maxTempList = newMaxTempList ?: emptyList()

        // Notificando o adapter para atualizar a UI
        notifyDataSetChanged()
    }
}
