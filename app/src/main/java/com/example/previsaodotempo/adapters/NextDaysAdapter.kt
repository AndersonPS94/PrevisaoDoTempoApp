package com.example.previsaodotempo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.previsaodotempo.databinding.ItemTodayNextdaysBinding
import com.example.previsaodotempo.model.data.Hourly




    class NextDaysAdapter (
        val list : List<Hourly>
    ) : Adapter<NextDaysAdapter.NextDaysViewHolder>() {

        inner class  NextDaysViewHolder(
            private val binding: ItemTodayNextdaysBinding
        ): RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Hourly) {

                // binding.textTime.text = item.time
                // binding.rvItemToday = item.temperature_2m
                // binding.imageWeather
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextDaysViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemTodayNextdaysBinding.inflate(inflater, parent, false)
        return NextDaysViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NextDaysViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)


    }
}