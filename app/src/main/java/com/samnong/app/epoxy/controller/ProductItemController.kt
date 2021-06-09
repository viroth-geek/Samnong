package com.samnong.app.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.epoxy.model.ProductModel_
import com.samnong.app.epoxy.model.loading
import com.samnong.app.model.Item
import java.util.concurrent.CopyOnWriteArrayList

class ProductItemController(
    private val itemClickListener: ItemClickListener,
) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private val _categoryProducts: CopyOnWriteArrayList<Item> = CopyOnWriteArrayList()
    private val _productModel: CopyOnWriteArrayList<ProductModel_> = CopyOnWriteArrayList()

    override fun buildModels() {
        if (_categoryProducts.isNotEmpty()) {
            if (_productModel.isNotEmpty()) {
                group {
                    id("group")
                    layout(R.layout.component_slider_view_group)
                    carousel {
                        id("carousel")
                        models(this@ProductItemController._productModel)
                        paddingDp(4)
                        hasFixedSize(true)
                    }
                }
            }
        } else {
            loading {
                id("loading")
            }
        }

    }

    fun submit(list: ArrayList<Item>) {
        _categoryProducts.addAll(list)
        _productModel.addAll(
            list.map { item ->
                ProductModel_()
                    .id("slider" + item.id)
                    .name(item.nameKh)
                    .price(item.prices?.get(0)?.price?.toString())
            }
        )
        requestModelBuild()
    }
}