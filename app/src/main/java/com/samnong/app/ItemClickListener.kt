package com.samnong.app

import com.samnong.app.model.CategoryElement
import com.samnong.app.model.Item

interface ItemClickListener {
    fun onProductCategoryItemClick(categoryElement: CategoryElement) {}
    fun onProductCategoryClick() {}
    fun onProductClick(item: Item) {}
}