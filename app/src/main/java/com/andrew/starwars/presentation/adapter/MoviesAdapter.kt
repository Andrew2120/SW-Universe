package com.andrew.starwars.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.starwars.databinding.MovieSearchItemBinding
import com.andrew.starwars.domain.model.MovieModel

class MoviesAdapter :
    PagingDataAdapter<MovieModel, MoviesAdapter.ViewHolder>(
        PAGE_DIFF_CALLBACK
    ) {

    inner class ViewHolder(itemBinding: MovieSearchItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val mItemBinding: MovieSearchItemBinding = itemBinding
        fun bind(planet: MovieModel) {
            mItemBinding.movie = planet
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
            MovieSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    companion object {
        private val PAGE_DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MovieModel>() {
                override fun areItemsTheSame(
                    oldItem: MovieModel,
                    newItem: MovieModel
                ): Boolean =
                    oldItem == newItem
                override fun areContentsTheSame(
                    oldItem: MovieModel,
                    newItem: MovieModel
                ): Boolean =
                    oldItem == newItem
            }
    }


    private var onItemClickListener: ((MovieModel) -> Unit)? = null

    fun onItemClickListener(item: (MovieModel) -> Unit) {
        onItemClickListener = item
    }


}