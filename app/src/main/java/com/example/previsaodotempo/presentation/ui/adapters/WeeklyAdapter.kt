package com.example.previsaodotempo.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.databinding.ItemTodayNextdaysBinding

// Adaptador para a RecyclerView da próxima semana
class WeeklyAdapter(

    // Listas de dados para cada item
    private var dateList: List<String> = emptyList(),
    private var minTempList: List<Double> = emptyList(),
    private var maxTempList: List<Double> = emptyList()
) : RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder>() {

    // ViewHolder para cada item da RecyclerView
    inner class WeeklyViewHolder(private val binding: ItemTodayNextdaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(date: String?, minTemp: Double?, maxTemp: Double?) {
            binding.textDayWeek.text = date ?: "-" // Evita crash se for nulo
            binding.textMin.text = minTemp?.let { "$it°C" } ?: "N/A°C"
            binding.textMax.text = maxTemp?.let { "$it°C" } ?: "N/A°C"
        }
    }

    // Criando o ViewHolder
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

    // Atualizando os dados no adapter
    fun updateData(newDateList: List<String>?, newMinTempList: List<Double>?, newMaxTempList: List<Double>?) {
        dateList = newDateList ?: emptyList()
        minTempList = newMinTempList ?: emptyList()
        maxTempList = newMaxTempList ?: emptyList()

        // Notificando o adapter para atualizar a UI
        notifyDataSetChanged()
    }
}
