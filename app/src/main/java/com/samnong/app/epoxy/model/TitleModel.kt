package com.samnong.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.samnong.app.R
import com.samnong.app.databinding.ComponentTitleBinding

@EpoxyModelClass(layout = R.layout.component_category_product)
abstract class TitleModel : EpoxyModelWithHolder<TitleModel.CategoryProductViewHolder>() {

    @field:EpoxyAttribute
    var categoryTitle: String? = null

    override fun bind(holder: CategoryProductViewHolder) {
        super.bind(holder)
        holder.apply {
            binding.categoryTextView.text = categoryTitle
        }
    }

    class CategoryProductViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentTitleBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentTitleBinding.bind(itemView)
        }
    }

}