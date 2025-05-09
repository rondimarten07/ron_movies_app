package com.rondi.ronmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.ItemEpisodeBinding
import com.rondi.ronmovies.domain.model.Episode

class EpisodeAdapter(
    private val tvId: Int,
    private val backgroundColor: Int
) : ListAdapter<Episode, EpisodeAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(val view: ItemEpisodeBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_episode, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.apply {
            tvId = this@EpisodeAdapter.tvId
            backgroundColor = this@EpisodeAdapter.backgroundColor
            episode = getItem(position)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean = oldItem.episodeNumber == newItem.episodeNumber
        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean = oldItem == newItem
    }
}