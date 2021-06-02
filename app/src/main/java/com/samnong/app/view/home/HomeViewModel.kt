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
    val categoryAndItems: MutableLiveData<ArrayList<CategoryAndItem>> = MutableLiveData()
    private val templeCategoryAndItems: ArrayList<CategoryAndItem> = arrayListOf()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getCategory() {

        viewModelScope.launch {
            when (val response = SamnongApp.mainRepository.getCategory()) {
                is ResultOf.Success -> {
                    product.postValue(response.data.data)
                }
                is ResultOf.Error -> {
                    println("Error ....")
                }
            }
        }
    }

    fun getItemByCategoryId(id: Int, title: String) {
        viewModelScope.launch {
            when (val response = SamnongApp.mainRepository.getItem(id = id)) {
                is ResultOf.Success -> {
                    val newItem = CategoryAndItem(categoryName = title, items = response.data.data)
                    categoryAndItems.postValue(arrayListOf(newItem))
                }
                is ResultOf.Error -> {
                    println("error get item ${response.exception}")
                }
            }
        }
    }
}