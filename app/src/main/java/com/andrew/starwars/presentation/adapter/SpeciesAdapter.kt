package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.SpeciesSearchItemBinding
import com.andrew.starwars.domain.model.SpeciesModel

class SpeciesAdapter :
    PagingDataAdapter<SpeciesModel, SpeciesAdapter.ViewHolder>(
        PAGE_DIFF_CALLBACK
    ) {

    inner class ViewHolder(itemBinding: SpeciesSearchItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val mItemBinding: SpeciesSearchItemBinding = itemBinding
        fun bind(planet: SpeciesModel) {
            mItemBinding.species = planet
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position).apply {

            holder.itemView.setOnClickListener {
                holder.bind(this!!)
                onItemClickListener?.let { it(this) }
            }
        }!!)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            SpeciesSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    companion object {
        private val PAGE_DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<SpeciesModel>() {
                override fun areItemsTheSame(
                    oldItem: SpeciesModel,
                    newItem: SpeciesModel
                ): Boolean =
                    oldItem == newItem
                override fun areContentsTheSame(
                    oldItem: SpeciesModel,
                    newItem: SpeciesModel
                ): Boolean =
                    oldItem == newItem
            }
    }


    private var onItemClickListener: ((SpeciesModel) -> Unit)? = null

    fun onItemClickListener(item: (SpeciesModel) -> Unit) {
        onItemClickListener = item
    }


}