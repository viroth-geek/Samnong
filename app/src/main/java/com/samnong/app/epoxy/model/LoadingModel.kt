package com.samnong.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.samnong.app.R
import com.samnong.app.databinding.ComponentCategoryBinding
import com.samnong.app.databinding.ComponentLoadingBinding

@EpoxyModelClass(layout = R.layout.component_loading)

abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.CategoryViewHolder>() {

    class CategoryViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentLoadingBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentLoadingBinding.bind(itemView)
        }
    }
}