package com.example.previsaodotempo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.previsaodotempo.databinding.ItemTodayNextdaysBinding
import com.example.previsaodotempo.model.data.HourlyUnits

class TomorrowAdapter(
    val list: List<HourlyUnits>
) : RecyclerView.Adapter<TomorrowAdapter.TomorrowViewHolder>() {

    inner class TomorrowViewHolder (
        private val binding: ItemTodayNextdaysBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HourlyUnits) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TomorrowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodayNextdaysBinding.inflate(inflater, parent, false)
        return TomorrowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TomorrowViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }
}