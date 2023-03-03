package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.MovieHomeItemBinding
import com.andrew.starwars.domain.model.MovieModel

class MoviesHomeAdapter : RecyclerView.Adapter<MoviesHomeAdapter.ViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return newItem == oldItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            MovieHomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: MovieModel = differ.currentList[position]
        holder.mItemBinding.movie = currentItem
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentItem) }
        }


    }

    class ViewHolder(itemBinding: MovieHomeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mItemBinding: MovieHomeItemBinding = itemBinding

    }


    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((MovieModel) -> Unit)? = null

    fun onItemClickListener(item: (MovieModel) -> Unit) {
        onItemClickListener = item
    }
}