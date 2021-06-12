package com.samnong.app.epoxy.model

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.samnong.app.R
import com.samnong.app.databinding.ComponentImageViewBinding
import com.samnong.app.utils.Constant

@EpoxyModelClass(layout = R.layout.component_image_view, useLayoutOverloads = true)
abstract class ImageViewModel : EpoxyModelWithHolder<ImageViewModel.ImageViewHolder>() {

    @field:EpoxyAttribute
    var url: String? = null

    override fun bind(holder: ImageViewHolder) {
        super.bind(holder)
        Glide
            .with(holder.binding.imageView)
            .load("${Constant.Url.imageUlr}/$url")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.binding.imageView)
    }

    class ImageViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentImageViewBinding
            private set
        override fun bindView(itemView: View) {
            binding = ComponentImageViewBinding.bind(itemView)
        }
    }

}