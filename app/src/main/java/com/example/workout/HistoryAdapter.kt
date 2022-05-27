package com.example.workout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.databinding.ItemHistoryRowBinding
class HistoryAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
        class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
            val historyitem = binding.lldisplayHistory
            val tvItem = binding.tvItems
            val tvText = binding.tvItemsDate

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder( ItemHistoryRowBinding.
            inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val model: String = items.get(position)
            holder.tvItem.text = (position + 1).toString()
            holder.tvText.text = model
            if (position % 2 == 0) {
                holder.historyitem.setBackgroundColor(Color.parseColor("#EBEBBE"))
            }
            else {
                holder.historyitem.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }


        override fun getItemCount(): Int {
            return items.size
        }
    }
