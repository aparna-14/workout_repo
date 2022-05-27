package com.example.workout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(private val items: ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.tvItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           return ViewHolder(
               ItemExerciseStatusBinding.
           inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
        when{
            model.getisSelected() ->{
                holder.tvItem.background = ContextCompat.
                getDrawable(holder.tvItem.context, R.drawable.item_circular_white_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getisCompleted() ->{
                holder.tvItem.background = ContextCompat.
                getDrawable(holder.tvItem.context, R.drawable.circular_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))

            }
            else ->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.tvItem.context,
                    R.drawable.item_circular_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}