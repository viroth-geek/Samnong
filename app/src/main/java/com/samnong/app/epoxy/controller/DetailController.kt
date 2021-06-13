package com.samnong.app.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.samnong.app.ItemClickListener
import com.samnong.app.R
import com.samnong.app.epoxy.model.*
import com.samnong.app.view.detail.DetailViewModel

class DetailController(
    private val viewModel: DetailViewModel,
    private val clickListener: ItemClickListener
) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels() {
        if (viewModel.detail != null) {
            val context = viewModel.context!!
            val item = viewModel.detail
            val imageModels = ArrayList<ImageViewModel_>()
            val itemModels: ArrayList<ProductModel_> = ArrayList()

            item?.gallery?.map { _item ->
                imageModels.add(
                    ImageViewModel_().id(_item).url(_item)
                )
            }

            imageView {
                id("image_view")
                url(item?.itemImg)
            }

            textView {
                id("name")
                text(item?.nameKh)
                appearance(R.style.Style_App_BigTitle)
            }

            textView {
                id("description")
                text(item?.descriptionKh)
                appearance(R.style.Style_App_BodyMainTitle)
            }
            title {
                id("gallery_title")
                categoryTitle(context.getString(R.string.gallery))
            }

            carousel {
                id("carousel_gallery")
                models(imageModels)
                paddingDp(4)
                hasFixedSize(true)
                numViewsToShowOnScreen(2f)
            }

            title {
                id("gallery_prices")
                categoryTitle(context.getString(R.string.prices))
            }

            item?.prices?.map { _item ->
                title {
                    id(_item.id)
                    categoryTitle("${_item.variation} ${context.getString(R.string.price)} $ ${_item.price} / ${_item.uom.nameKh}")
                }
            }

            title {
                id("contact_title")
                categoryTitle(context.getString(R.string.contact_number))
            }

            item?.mobile?.map { _item ->
                title {
                    id("contact_title_${_item.id}")
                    categoryTitle("${_item.company.name} : ${_item.phone}")
                }
            }
            if (viewModel.relatedProducts.isNotEmpty()) {
                viewModel.relatedProducts.map { product ->
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

                group {
                    id("group")
                    layout(R.layout.component_slider_view_group)
                    carousel {
                        id("carousel_gallery1")
                        models(itemModels)
                        paddingDp(4)
                        hasFixedSize(true)
                        numViewsToShowOnScreen(2f)
                    }
                    shouldSaveViewState(true)
                }

                group {
                    id("group2")
                    layout(R.layout.component_slider_view_group)
                    carousel {
                        id("carousel_gallery1")
                        models(itemModels)
                        paddingDp(4)
                        hasFixedSize(true)
                        numViewsToShowOnScreen(2f)
                    }
                    shouldSaveViewState(true)
                }
                group {
                    id("group3")
                    layout(R.layout.component_slider_view_group)
                    carousel {
                        id("carousel_gallery1")
                        models(itemModels)
                        paddingDp(4)
                        hasFixedSize(true)
                        numViewsToShowOnScreen(2f)
                    }
                    shouldSaveViewState(true)
                }

            }
        } else {
            loading {
                id("loading")
            }
        }
    }

}