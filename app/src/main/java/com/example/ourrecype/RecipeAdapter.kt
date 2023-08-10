package com.example.ourrecype

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ourrecype.databinding.ItemRecipeBinding

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position], position = position)

    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ScheduleViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipesModel.Results, position: Int) {
            binding.run {
                tvTitle.text = item.title
                Glide.with(root.context)
                    .load(item.image)
                    .into(ivRecipe)

                root.setOnClickListener {
                    onItemClickListener?.invoke(item.id)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<RecipesModel.Results>() {
        override fun areItemsTheSame(
            oldExampleModel: RecipesModel.Results, newExampleModel: RecipesModel.Results
        ): Boolean {
            return oldExampleModel.id == newExampleModel.id
        }

        override fun areContentsTheSame(
            oldExampleModel: RecipesModel.Results, newExampleModel: RecipesModel.Results
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}