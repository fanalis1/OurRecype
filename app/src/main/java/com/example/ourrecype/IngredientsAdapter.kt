package com.example.ourrecype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ourrecype.databinding.ItemIngredientsBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemIngredientsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position], position = position)

    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ScheduleViewHolder(private val binding: ItemIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailRecipeModel.Ingredients, position: Int) {
            binding.run {
                tvIngredient.text = "${item.name} ${item.amount?.toInt()} ${item.unit}"
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<DetailRecipeModel.Ingredients>() {
        override fun areItemsTheSame(
            oldExampleModel: DetailRecipeModel.Ingredients, newExampleModel: DetailRecipeModel.Ingredients
        ): Boolean {
            return oldExampleModel.name == newExampleModel.name
        }

        override fun areContentsTheSame(
            oldExampleModel: DetailRecipeModel.Ingredients, newExampleModel: DetailRecipeModel.Ingredients
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

}