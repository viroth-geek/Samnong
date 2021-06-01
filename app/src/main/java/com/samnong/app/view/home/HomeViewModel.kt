package com.samnong.app.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.model.CategoryAndItem
import com.samnong.app.model.CategoryElement
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var product: MutableLiveData<ArrayList<CategoryElement>> = MutableLiveData(arrayListOf())
    val categoryAndItems: MutableLiveData<ArrayList<CategoryAndItem>> = MutableLiveData(arrayListOf())
    private val templeCategoryAndItems: ArrayList<CategoryAndItem> = arrayListOf()
    var loading: MutableLiveData<Boolean> = MutableLiveData()


    fun getCategory() {
        viewModelScope.launch {
            when (val response = SamnongApp.mainRepository.getCategory()) {
                is ResultOf.Success -> {
                    product.postValue(response.data.data)
                    response.data.data.forEachIndexed { index, item ->
                        if (index <= 2) {
                            getItemByCategoryId(id = item.id, title = item.nameKh)
                        }
                    }
                }
                is ResultOf.Error -> {
                    println("error get category ${response.exception}")
                }
            }
        }
    }

    private fun getItemByCategoryId(id: Int, title: String) {
        viewModelScope.launch {
            when (val response = SamnongApp.mainRepository.getItem(id = id)) {
                is ResultOf.Success -> {
                    val newItem = CategoryAndItem(categoryName = title, items = response.data.data)
                    templeCategoryAndItems.add(newItem)
                    categoryAndItems.postValue(templeCategoryAndItems)
                }
                is ResultOf.Error -> {
                    println("error get item ${response.exception}")
                }
            }
        }
    }
}