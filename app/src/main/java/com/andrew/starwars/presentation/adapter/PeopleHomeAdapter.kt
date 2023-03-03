package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.PersonHomeItemBinding
import com.andrew.starwars.domain.model.PersonModel

class PeopleHomeAdapter : RecyclerView.Adapter<PeopleHomeAdapter.ViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<PersonModel>() {
        override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
            return newItem == oldItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            PersonHomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: PersonModel = differ.currentList[position]
        holder.mItemBinding.person = currentItem
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentItem) }
        }


    }

    class ViewHolder(itemBinding: PersonHomeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mItemBinding: PersonHomeItemBinding = itemBinding

    }


    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((PersonModel) -> Unit)? = null

    fun onItemClickListener(item: (PersonModel) -> Unit) {
        onItemClickListener = item
    }
}