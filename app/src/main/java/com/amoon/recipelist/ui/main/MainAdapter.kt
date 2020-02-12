package com.amoon.recipelist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amoon.recipelist.R
import com.amoon.recipelist.data.model.Drink
import com.amoon.recipelist.databinding.MainAdapterItemsBinding


class MainAdapter(private val listener: OnClickListener) :
    PagedListAdapter<Drink, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }
        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }
    }) {



    override fun getItemViewType(position: Int): Int {
        return    R.layout.main_adapter_items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            R.layout.main_adapter_items -> MainViewHolder(
                listener,
                MainAdapterItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {

            R.layout.main_adapter_items -> (holder as MainViewHolder).bind(
                    getItem(position )
            )
        }
        listener
    }

    fun updateData() {
        this.notifyDataSetChanged()
    }


    interface OnClickListener {
        fun onItemClick(drink: Drink)
    }


}