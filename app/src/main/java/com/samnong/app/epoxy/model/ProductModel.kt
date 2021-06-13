package com.samnong.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.samnong.app.R
import com.samnong.app.databinding.ComponentProductItemBinding
import com.samnong.app.utils.Constant

@EpoxyModelClass(layout = R.layout.component_product_item)
abstract class ProductModel : EpoxyModelWithHolder<ProductModel.ProductViewHolder>() {

    @field:EpoxyAttribute
    var image: String? = null

    @field:EpoxyAttribute
    var name: String? = null

    @field:EpoxyAttribute
    var price: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var clickListener: View.OnClickListener? = null

    override fun bind(holder: ProductViewHolder) {
        super.bind(holder)
        holder.apply {
            Glide
                .with(binding.imageView)
                .load("${Constant.Url.imageUlr}/$image")
                .circleCrop()
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
            binding.productNameTextView.text = name
            binding.productPriceTextview.text = price
            binding.root.setOnClickListener(clickListener)
        }
    }

    class ProductViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentProductItemBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentProductItemBinding.bind(itemView)
        }
    }
}