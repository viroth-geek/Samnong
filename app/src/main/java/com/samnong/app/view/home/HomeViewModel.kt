package com.samnong.app.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.epoxy.controller.CategoryController
import com.samnong.app.model.CategoryAndItem
import com.samnong.app.model.CategoryElement
import com.samnong.app.model.Content
import com.samnong.app.model.Item
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _categories: MutableLiveData<ArrayList<CategoryElement>> = MutableLiveData(ArrayList())
    var categories: LiveData<ArrayList<CategoryElement>> = _categories

    val showDetail: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _content = MutableLiveData<List<Content>>()
    val content : LiveData<List<Content>> = _content

    init {
        generateContent()
    }

    private fun generateContent() {

        val cards = listOf(1..8).flatten()
        _content.value = listOf(
            Content.Carousel("100", cards),
            Content.Banner("1"),
            Content.Carousel("101", cards),
            Content.Carousel("102", cards),
            Content.Carousel("103", cards),
            Content.Banner("2"),
            Content.Carousel("104", cards),
            Content.Carousel("106", cards),
            Content.Banner("3"),
            Content.Carousel("108", cards),
            Content.Banner("4"),
            Content.Carousel("109", cards),
            Content.Carousel("105", cards),
        )
    }

    fun getCategory(controller: CategoryController) {
        if (categories.value?.isNotEmpty() == true) {
            controller.requestModelBuild()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getCategory()) {
                is ResultOf.Success -> {
                    _categories.postValue(response.data.data)
                    controller.requestModelBuild()
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

                }
                is ResultOf.Error -> {
                    println("error get item ${response.exception}")
                }
            }
        }

    }
}