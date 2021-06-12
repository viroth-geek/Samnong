package com.samnong.app.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.samnong.app.R
import com.samnong.app.databinding.ComponentTitleBinding
import org.w3c.dom.Attr

@EpoxyModelClass(layout = R.layout.component_title, useLayoutOverloads = true)
abstract class TextViewModel : EpoxyModelWithHolder<TextViewModel.TextViewHolder>() {

    @field:EpoxyAttribute
    var text: String? = null

    @field:EpoxyAttribute
    var appearance: Int? = null


    override fun shouldSaveViewState(): Boolean = true

    override fun bind(holder: TextViewHolder) {
        super.bind(holder)
        holder.binding.titleTextView.text = text
        holder.binding.titleTextView.setTextAppearance(appearance!!)
    }

    class TextViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentTitleBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentTitleBinding.bind(itemView)
        }
    }

}