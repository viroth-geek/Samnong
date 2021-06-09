package com.samnong.app.model

sealed class CategoryAndItem(val id: String) {
    class Title(id: String) : CategoryAndItem(id)
    class Carousel(id: String, val list: ArrayList<Item> = ArrayList()) : CategoryAndItem(id)
}