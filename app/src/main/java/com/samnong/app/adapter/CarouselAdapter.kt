package com.samnong.app.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samnong.app.databinding.ComponentCardBinding

class CarouselAdapter : ListAdapter<Int, CarouselAdapter.CardViewHolder>(DiffUtilCarouselItem()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            binding = ComponentCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardViewHolder(val binding: ComponentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(index: Int) {
            binding.txtCard.text = index.toString()
            when (index) {
                1 -> binding.txtCard.setBackgroundColor(Color.parseColor("#FFF70D"))
                2 -> binding.txtCard.setBackgroundColor(Color.parseColor("#EBD20C"))
                3 -> binding.txtCard.setBackgroundColor(Color.parseColor("#FFCC00"))
                4 -> binding.txtCard.setBackgroundColor(Color.parseColor("#E8AA0C"))
                5 -> binding.txtCard.setBackgroundColor(Color.parseColor("#FFA70F"))
                6 -> binding.txtCard.setBackgroundColor(Color.parseColor("#FFA721"))
                7 -> binding.txtCard.setBackgroundColor(Color.parseColor("#E87B13"))
                8 -> binding.txtCard.setBackgroundColor(Color.parseColor("#FF6912"))
                else -> binding.txtCard.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    private class DiffUtilCarouselItem : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
    }
}