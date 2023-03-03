package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.PersonHomeItemBinding
import com.andrew.starwars.databinding.PlanetsHomeItemBinding
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.PlanetModel

class PlanetsHomeAdapter : RecyclerView.Adapter<PlanetsHomeAdapter.ViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<PlanetModel>() {
        override fun areItemsTheSame(oldItem: PlanetModel, newItem: PlanetModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PlanetModel, newItem: PlanetModel): Boolean {
            return newItem == oldItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            PlanetsHomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: PlanetModel = differ.currentList[position]
        holder.mItemBinding.planet = currentItem
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentItem) }
        }


    }

    class ViewHolder(itemBinding: PlanetsHomeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mItemBinding: PlanetsHomeItemBinding = itemBinding

    }


    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((PlanetModel) -> Unit)? = null

    fun onItemClickListener(item: (PlanetModel) -> Unit) {
        onItemClickListener = item
    }
}