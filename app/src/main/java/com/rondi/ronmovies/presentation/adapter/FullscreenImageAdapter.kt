package com.rondi.ronmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.ItemFullscreenImageBinding
import com.rondi.ronmovies.domain.model.Image

class FullscreenImageAdapter(
    private val onClick: () -> Unit
) : ListAdapter<Image, FullscreenImageAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(val view: ItemFullscreenImageBinding) : RecyclerView.ViewHolder(view.root) {
        init {
            view.photoView.setOnClickListener { onClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_fullscreen_image, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.image = getItem(position)
    }

    object DiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem.filePath == newItem.filePath
        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem == newItem
    }
}