package com.samnong.app.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.samnong.app.R
import com.samnong.app.adapter.ProductItemAdapter
import com.samnong.app.epoxy.model.CategoryModel_
import com.samnong.app.epoxy.model.categoryProduction
import com.samnong.app.epoxy.model.title
import com.samnong.app.epoxy.myCarousel
import com.samnong.app.view.home.HomeViewModel

class CategoryController(
    var viewModel: HomeViewModel,
    var context: Context
) : AsyncEpoxyController() {

    override fun buildModels() {
//        if (viewModel.categories.value?.isNotEmpty() == true) {
//            val titleModels: ArrayList<CategoryModel_> = viewModel.titleModels
//            viewModel.categories.value?.forEach { item ->
//                titleModels.add(
//                    CategoryModel_()
//                        .id(item.id)
//                        .name(item.nameKh)
//                )
//            }
//
//            group {
//                id("group")
//                layout(R.layout.component_slider_view_group)
//                carousel {
//                    id("title_carousel")
//                    models(titleModels)
//                    paddingDp(4)
//                    hasFixedSize(true)
//                }
//                shouldSaveViewState(true)
//            }
//
//            myCarousel {
//                id("my_carouse")
//                models(titleModels)
//            }
//
//        } else {
//            title {
//                id("empty")
//                categoryTitle("Loading.....")
//            }
//        }

//        if (_titles.isNotEmpty()) {
//            _titles.forEachIndexed { index, item ->
//
//                val itemModels: ArrayList<ProductModel_> = ArrayList()
//                item.items.forEach { item2 ->
//                    itemModels.add(
//                        ProductModel_()
//                            .id(item2.id)
//                            .image(item2.itemImg)
//                            .name(item2.nameKh)
//                            .price("${item2.prices?.get(0)?.price} $ / ${item2.prices?.get(0)?.uom?.nameKh}")
//                    )
//                }
//
//                title {
//                    id("$index")
//                    categoryTitle(item.categoryName)
//                }
//
//                group {
//                    id("$index")
//                    layout(R.layout.component_slider_view_group)
//                    carousel {
//                        id("$index")
//                        models(itemModels)
//                        numViewsToShowOnScreen(2.5f)
//                        paddingDp(4)
//                        hasFixedSize(true)
//                    }
//                }
//            }
//        }
    }
}