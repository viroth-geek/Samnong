package com.samnong.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.databinding.ComponentCardBinding
import com.samnong.app.model.Item
import com.samnong.app.utils.Constant

class CarouselAdapter(private val itemClickListener: ItemClickListener) :
    ListAdapter<Item, CarouselAdapter.CardViewHolder>(DiffUtilCarouselItem()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            binding = ComponentCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener = itemClickListener
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardViewHolder(
        val binding: ComponentCardBinding,
        val itemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            binding.productNameTextView.text = item.nameKh
            binding.productPriceTextview.text =
                "${item.prices?.get(0)?.price} / ${item.prices?.get(0)?.uom?.nameKh}"
            Glide
                .with(binding.imageView)
                .load("${Constant.Url.imageUlr}/${item.itemImg}")
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
            binding.root.setOnClickListener {
                itemClickListener.itemClick(item)
            }
        }
    }

    private class DiffUtilCarouselItem : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }
}