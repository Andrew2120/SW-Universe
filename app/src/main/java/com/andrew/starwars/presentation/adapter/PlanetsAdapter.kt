package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.PlanetSearchItemBinding
import com.andrew.starwars.domain.model.PlanetModel

class PlanetsAdapter :
    PagingDataAdapter<PlanetModel, PlanetsAdapter.ViewHolder>(
        PAGE_DIFF_CALLBACK
    ) {

    inner class ViewHolder(itemBinding: PlanetSearchItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val mItemBinding: PlanetSearchItemBinding = itemBinding
        fun bind(planet: PlanetModel) {
            mItemBinding.planet = planet
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
            PlanetSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    companion object {
        private val PAGE_DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PlanetModel>() {
                override fun areItemsTheSame(
                    oldItem: PlanetModel,
                    newItem: PlanetModel
                ): Boolean =
                    oldItem == newItem
                override fun areContentsTheSame(
                    oldItem: PlanetModel,
                    newItem: PlanetModel
                ): Boolean =
                    oldItem == newItem
            }
    }


    private var onItemClickListener: ((PlanetModel) -> Unit)? = null

    fun onItemClickListener(item: (PlanetModel) -> Unit) {
        onItemClickListener = item
    }


}