package com.rondi.ronmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.ItemPersonBinding
import com.rondi.ronmovies.domain.model.Person

class PersonAdapter(
    private val isGrid: Boolean = false,
    private val isCast: Boolean = false,
) : ListAdapter<Person, PersonAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(val view: ItemPersonBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_person, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.apply {
            isGrid = this@PersonAdapter.isGrid
            isCast = this@PersonAdapter.isCast
            person = getItem(position)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
    }
}