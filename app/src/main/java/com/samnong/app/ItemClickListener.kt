package com.samnong.app

import com.samnong.app.model.CategoryElement
import com.samnong.app.model.Item

interface ItemClickListener {
    fun itemClick(item: Item)
}