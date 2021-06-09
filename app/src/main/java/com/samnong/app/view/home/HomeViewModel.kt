package com.samnong.app.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.model.CategoryAndItem
import com.samnong.app.model.Item
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val content: MutableLiveData<ArrayList<CategoryAndItem>> = MutableLiveData(ArrayList())
    private var tempContent: ArrayList<CategoryAndItem> = ArrayList()

//    private val _content = MutableLiveData<List<CategoryAndItem>>()
//    val content: LiveData<List<CategoryAndItem>> = _content

//    init {
//        generateContent()
//    }
//
//    private fun generateContent() {
//
//        val cards = ArrayList<Item>()
//        List(10) {
//            cards.add(
//                Item(
//                    id = it.toLong(),
//                    clientID = 1,
//                    itemImg = "",
//                    itemType = "",
//                    code = "",
//                    nameCh = "",
//                    nameEn = "",
//                    nameKh = "",
//                    vdoURL = "",
//                    ordering = "",
//                    prices = null
//                )
//            )
//        }
//
//        _content.value = listOf(
//            CategoryAndItem.Carousel("100", cards),
//            CategoryAndItem.Title("1"),
//            CategoryAndItem.Carousel("101", cards),
//            CategoryAndItem.Carousel("102", cards),
//            CategoryAndItem.Carousel("103", cards),
//            CategoryAndItem.Title("2"),
//            CategoryAndItem.Carousel("104", cards),
//            CategoryAndItem.Carousel("106", cards),
//            CategoryAndItem.Title("3"),
//            CategoryAndItem.Carousel("108", cards),
//            CategoryAndItem.Title("4"),
//            CategoryAndItem.Carousel("109", cards),
//            CategoryAndItem.Carousel("105", cards),
//        )
//    }

    fun getCategory() {
        if (content.value?.isNotEmpty() == true) return
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getCategory()) {
                is ResultOf.Success -> {
                    response.data.data.forEachIndexed { index, item ->
                        if (index <= 10) {
                            getItemByCategoryId(id = item.id, title = item.nameKh)
                        } else {
                            return@forEachIndexed
                        }
                    }
                }

                is ResultOf.Error -> {
                    println("Error .... ${response.exception}")
                }
            }
        }
    }

    private fun getItemByCategoryId(id: Int, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getItem(id = id)) {
                is ResultOf.Success -> {
                    val titleItem = CategoryAndItem.Title(id = title)
                    val carouselItem = CategoryAndItem.Carousel(id = title, list = response.data.data)
                    tempContent.add(titleItem)
                    tempContent.add(carouselItem)
                    content.postValue(tempContent)
                }
                is ResultOf.Error -> {
                    println("error get item ${response.exception}")
                }
            }
        }

    }
}