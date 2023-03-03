package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.PersonSearchItemBinding
import com.andrew.starwars.domain.model.PersonModel

class PeopleAdapter :
    PagingDataAdapter<PersonModel, PeopleAdapter.ViewHolder>(
        PAGE_DIFF_CALLBACK
    ) {

    inner class ViewHolder(itemBinding: PersonSearchItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val mItemBinding: PersonSearchItemBinding = itemBinding
        fun bind(planet: PersonModel) {
            mItemBinding.person = planet
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
            PersonSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    companion object {
        private val PAGE_DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PersonModel>() {
                override fun areItemsTheSame(
                    oldItem: PersonModel,
                    newItem: PersonModel
                ): Boolean =
                    oldItem == newItem
                override fun areContentsTheSame(
                    oldItem: PersonModel,
                    newItem: PersonModel
                ): Boolean =
                    oldItem == newItem
            }
    }


    private var onItemClickListener: ((PersonModel) -> Unit)? = null

    fun onItemClickListener(item: (PersonModel) -> Unit) {
        onItemClickListener = item
    }


}