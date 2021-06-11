package com.samnong.app.utils

import androidx.recyclerview.widget.RecyclerView

interface NestedRecyclerViewViewHolder {
    fun getId(): String
    fun getLayoutManager(): RecyclerView.LayoutManager?
}