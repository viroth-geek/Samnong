package com.samnong.app.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.samnong.app.R
import com.samnong.app.epoxy.model.*
import com.samnong.app.view.detail.DetailViewModel

class DetailController(
    private val viewModel: DetailViewModel,
) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels() {
        if (viewModel._detail != null) {
            val context = viewModel.context!!
            val item = viewModel._detail
            val imageModels = ArrayList<ImageViewModel_>()
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
                appearance(R.style.Style_App_BodyMainTitle)
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

            item?.prices?.map { _item ->

            }

        } else {
            loading {
                id("loading")
            }
        }
    }

}