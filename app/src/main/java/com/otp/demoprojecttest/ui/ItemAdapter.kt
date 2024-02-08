package com.otp.demoprojecttest.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.otp.demoprojecttest.databinding.ItemLayoutBinding
import com.otp.demoprojecttest.model.Item

class ItemAdapter(private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    fun updateItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
    }

    class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            Log.d("ItemAdapter", "Loading image for ${item.name}")
            Glide.with(binding.root)
                .load(item.imgurl)
                .into(binding.imgView)

            binding.apply {
                tvName.text = item.name
                tvTag.text = "Tag: ${item.tag}"
                tvOrder.text = "Order: ${item.order}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
