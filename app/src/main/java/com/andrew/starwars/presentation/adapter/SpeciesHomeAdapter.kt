package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.SpeciesHomeItemBinding
import com.andrew.starwars.domain.model.SpeciesModel

class SpeciesHomeAdapter : RecyclerView.Adapter<SpeciesHomeAdapter.ViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<SpeciesModel>() {
        override fun areItemsTheSame(oldItem: SpeciesModel, newItem: SpeciesModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SpeciesModel, newItem: SpeciesModel): Boolean {
            return newItem == oldItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            SpeciesHomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: SpeciesModel = differ.currentList[position]
        holder.mItemBinding.species = currentItem
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentItem) }
        }


    }

    class ViewHolder(itemBinding: SpeciesHomeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mItemBinding: SpeciesHomeItemBinding = itemBinding

    }


    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((SpeciesModel) -> Unit)? = null

    fun onItemClickListener(item: (SpeciesModel) -> Unit) {
        onItemClickListener = item
    }
}