package com.rondi.ronmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.ItemGenreBinding
import com.rondi.ronmovies.domain.model.Genre
import com.rondi.ronmovies.util.Detail

class GenreAdapter(
    private val detailType: Detail
) : ListAdapter<Pair<Int, String>, GenreAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(val view: ItemGenreBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_genre, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.apply {
            detailType = this@GenreAdapter.detailType
            genre = Genre(id = getItem(position).first, name = getItem(position).second)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Pair<Int, String>>() {
        override fun areItemsTheSame(oldItem: Pair<Int, String>, newItem: Pair<Int, String>): Boolean = oldItem.first == newItem.first
        override fun areContentsTheSame(oldItem: Pair<Int, String>, newItem: Pair<Int, String>): Boolean = oldItem == newItem
    }
}