package com.samnong.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.samnong.app.R
import com.samnong.app.databinding.ComponentCategoryBinding

@EpoxyModelClass(layout = R.layout.component_category)
abstract class CategoryModel : EpoxyModelWithHolder<CategoryModel.CategoryViewHolder>() {

    @field:EpoxyAttribute
    var name: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var clickListener: View.OnClickListener? = null

    override fun bind(holder: CategoryViewHolder) {
        super.bind(holder)
        holder.apply {
            binding.categoryChipView.text = name
            binding.categoryChipView.setOnClickListener(clickListener)
        }
    }

    class CategoryViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentCategoryBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentCategoryBinding.bind(itemView)
        }
    }
}