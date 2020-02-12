package com.amoon.recipelist.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.amoon.recipelist.data.model.Drink
import com.amoon.recipelist.databinding.MainAdapterItemsBinding
import com.thekhaeng.pushdownanim.PushDownAnim

class MainViewHolder(
    private val listener: MainAdapter.OnClickListener,
    private val binding: MainAdapterItemsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Drink?) {

        binding.items = item

        Glide.with(itemView).load(binding.items?.strDrinkThumb).into(binding.image)

        PushDownAnim.setPushDownAnimTo(itemView).setScale(PushDownAnim.MODE_STATIC_DP, 5F)
            .setOnClickListener {
                binding.items?.let { it1 -> listener.onItemClick(it1) }
            }

    }
}