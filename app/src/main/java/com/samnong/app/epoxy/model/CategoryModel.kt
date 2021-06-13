package com.samnong.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.chip.Chip
import com.google.android.material.textview.MaterialTextView
import com.samnong.app.R
import com.samnong.app.epoxy.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.component_category)
abstract class CategoryModel : EpoxyModelWithHolder<CategoryModel.CategoryViewHolder>() {

    @field:EpoxyAttribute
    var name: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var clickListener: View.OnClickListener? = null

    override fun shouldSaveViewState(): Boolean = true

    override fun bind(holder: CategoryViewHolder) {
        super.bind(holder)
        holder.titleView.text = name
        holder.titleView.setOnClickListener(clickListener)
    }

    class CategoryViewHolder : KotlinEpoxyHolder() {
        val titleView by bind<Chip>(R.id.categoryChipView)
    }
}