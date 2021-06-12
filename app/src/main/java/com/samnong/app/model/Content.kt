package com.samnong.app.model

sealed class Content(val id: String) {
    class Banner(id: String) : Content(id)
    class Carousel(id: String, val list: List<Int>) : Content(id)
}