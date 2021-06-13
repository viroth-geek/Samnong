package com.samnong.app.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.epoxy.model.CategoryModel_
import com.samnong.app.epoxy.model.ProductModel_
import com.samnong.app.epoxy.model.title
import com.samnong.app.view.home.HomeViewModel

class CategoryController(
    private var viewModel: HomeViewModel,
    private var context: Context,
    private var clickListener: ItemClickListener,
) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels() {
        if (viewModel.categoriesAndItems.value?.isNotEmpty() == true) {

            val titleModels = ArrayList<CategoryModel_>()
            val itemModels: ArrayList<ProductModel_> = ArrayList()

            viewModel.categoriesAndItems.value?.forEachIndexed { index, item ->
                titleModels.add(
                    CategoryModel_()
                        .id("title_$index")
                        .name(item.categoryName)
                        .clickListener { _, _, _, _ ->
                            clickListener.onProductCategoryClick()
                        }
                )

                title {
                    id("${index}_${item.categoryName}")
                    categoryTitle(item.categoryName)
                }

                item.items.forEach { product ->
                    itemModels.add(
                        ProductModel_()
                            .id(product.id)
                            .image(product.itemImg)
                            .name(product.nameKh)
                            .price("${product.prices?.get(0)?.price} $ / ${product.prices?.get(0)?.uom?.nameKh}")
                            .clickListener { _, _, _, _ ->
                                clickListener.onProductClick(item = product)
                            }
                    )

                }
            }

            group {
                id("group")
                layout(R.layout.component_slider_view_group)
                carousel {
                    id("_carousel")
                    models(titleModels)
                    paddingDp(4)
                    hasFixedSize(true)
                }
                shouldSaveViewState(true)
            }

            group {
                id("group_1")
                layout(R.layout.component_slider_view_group)
                carousel {
                    id("_carousel_1")
                    models(itemModels)
                    paddingDp(4)
                    hasFixedSize(true)
                }
                shouldSaveViewState(true)
            }

        } else {
            title {
                id("empty")
                categoryTitle("Loading.....")
            }
        }
    }
}