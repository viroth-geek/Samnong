package com.samnong.app.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.epoxy.model.CategoryModel_
import com.samnong.app.epoxy.model.ProductModel_
import com.samnong.app.epoxy.model.title
import com.samnong.app.model.CategoryAndItem
import com.samnong.app.model.CategoryElement
import java.util.concurrent.CopyOnWriteArrayList

class CategoryController(private val itemClickListener: ItemClickListener) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private val _products: CopyOnWriteArrayList<CategoryElement> = CopyOnWriteArrayList()
    private val _categoryModels: CopyOnWriteArrayList<CategoryModel_> = CopyOnWriteArrayList()
    private val _categoryAndItem: CopyOnWriteArrayList<Pair<String, List<ProductModel_>>> = CopyOnWriteArrayList()


    override fun buildModels() {
        if (_categoryModels.isNotEmpty()) {
            group {
                id("group")
                layout(R.layout.component_slider_view_group)
                carousel {
                    id("carousel")
                    models(_categoryModels)
                    paddingDp(4)
                    hasFixedSize(true)
                }
            }
        }
        if (_categoryAndItem.isNotEmpty()) {
            _categoryAndItem.forEach { item ->
                title {
                    id(item.first)
                    categoryTitle(item.first)
                }
                group {
                    id("group")
                    layout(R.layout.component_slider_view_group)
                    carousel {
                        id("carousel")
                        models(item.second)
                        paddingDp(4)
                        hasFixedSize(true)
                    }
                }
            }
        }
    }

    fun submitProduct(list: ArrayList<CategoryElement>) {
        _products.addAll(list)
        _categoryModels.clear()
        _categoryModels.addAll(
            list.map { item ->
                CategoryModel_()
                    .id("slider" + item.id)
                    .name(item.nameKh)
            }
        )
        requestModelBuild()
    }

    fun submitItem(list: ArrayList<CategoryAndItem>) {
        list.map { item ->
            _categoryAndItem.add(
                Pair(
                    first = item.categoryName,
                    second = item.items.map { item2 ->
                        ProductModel_()
                            .image(item2.itemImg)
                            .name(item2.nameKh)
                            .price(item2.nameKh)
                    }
                )
            )
        }
    }
}