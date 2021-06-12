package com.samnong.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.databinding.ComponentBannerBinding
import com.samnong.app.databinding.ComponentCarouselBinding
import com.samnong.app.model.Content
import com.samnong.app.utils.NestedRecyclerViewStateRecoverAdapter
import com.samnong.app.utils.NestedRecyclerViewViewHolder

private enum class ViewType {
    BANNER,
    CAROUSEL,
}

class ContentAdapter(private val itemClickListener: ItemClickListener? = null) : NestedRecyclerViewStateRecoverAdapter<Content, ContentAdapter.ViewHolder>(ContentAdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.BANNER.ordinal ->
                ViewHolder.BannerViewHolder(
                    binding = ComponentBannerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                )

            ViewType.CAROUSEL.ordinal ->
                ViewHolder.CarouselViewHolder(
                    binding = ComponentCarouselBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                )

            else -> throw ClassNotFoundException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Content.Banner -> ViewType.BANNER.ordinal
            is Content.Carousel -> ViewType.CAROUSEL.ordinal
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.BANNER.ordinal -> (holder as ViewHolder.BannerViewHolder).bind(getItem(position) as Content.Banner)
            ViewType.CAROUSEL.ordinal -> (holder as ViewHolder.CarouselViewHolder).bind(
                getItem(
                    position
                ) as Content.Carousel
            )
            else -> throw ClassNotFoundException()
        }
        super.onBindViewHolder(holder, position)
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class BannerViewHolder(
            private val binding: ComponentBannerBinding,
        ) : ViewHolder(binding.root) {

            fun bind(content: Content.Banner) {
                with(binding) {
                    txtBanner.text = root.context.getString(R.string.banner_text, content.id)
                }
            }
        }

        class CarouselViewHolder(
            private val binding: ComponentCarouselBinding,
            ) : ViewHolder(binding.root), NestedRecyclerViewViewHolder {

            private lateinit var content: Content.Carousel

            init {
                binding.carousel.apply {
                    adapter = CarouselAdapter()
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }
            }

            fun bind(content: Content.Carousel) {
                this.content = content
                with(binding) {
                    (carousel.adapter as CarouselAdapter).submitList(content.list)
                }
            }

            override fun getId() = content.id

            override fun getLayoutManager() = binding.carousel.layoutManager
        }
    }

    private class ContentAdapterDiffUtil : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) =
            oldItem::class.simpleName == newItem::class.simpleName

        override fun areContentsTheSame(oldItem: Content, newItem: Content) =
            oldItem.id == newItem.id

    }
}
