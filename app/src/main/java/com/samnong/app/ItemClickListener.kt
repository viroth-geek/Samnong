package com.samnong.app

import com.samnong.app.model.CategoryElement

interface ItemClickListener {
    fun onProductCategoryItemClick(categoryElement: CategoryElement) {}
    fun onProductCategoryClick() {}
    fun onPress(value: String) {}
}