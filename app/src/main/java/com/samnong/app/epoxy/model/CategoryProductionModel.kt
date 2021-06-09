package com.samnong.app.epoxy.model

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.samnong.app.R
import com.samnong.app.adapter.ProductItemAdapter
import com.samnong.app.databinding.ComponentCategoryProductBinding
import com.samnong.app.epoxy.controller.ProductItemController

@EpoxyModelClass(layout = R.layout.component_category_product)
abstract class CategoryProductionModel : EpoxyModelWithHolder<CategoryProductionModel.CategoryProductViewHolder>() {

    @field:EpoxyAttribute
    var context: Context? = null

    @field:EpoxyAttribute
    var itemController: ProductItemAdapter? = null

    override fun bind(holder: CategoryProductViewHolder) {
        super.bind(holder)
        holder.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = itemController
        }
    }

    class CategoryProductViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentCategoryProductBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentCategoryProductBinding.bind(itemView)
        }
    }

}