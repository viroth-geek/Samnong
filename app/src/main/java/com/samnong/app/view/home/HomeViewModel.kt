package com.samnong.app.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.epoxy.controller.CategoryController
import com.samnong.app.model.CategoryAndItem
import com.samnong.app.model.CategoryElement
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val showDetail: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _content: MutableLiveData<ArrayList<CategoryAndItem>> = MutableLiveData(ArrayList())
    var content: LiveData<ArrayList<CategoryAndItem>> = _content
    private val _categories: MutableLiveData<ArrayList<CategoryElement>> = MutableLiveData(ArrayList())

    var categories: LiveData<ArrayList<CategoryElement>> = _categories
    val categoriesAndItems: MutableLiveData<ArrayList<CategoryAndItem>> = MutableLiveData(ArrayList())
    private var tempCategoryAndItem: ArrayList<CategoryAndItem> = ArrayList()

    fun getCategory(controller: CategoryController) {
        if (tempCategoryAndItem.isNotEmpty()) {
            categoriesAndItems.postValue(tempCategoryAndItem)
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getCategory()) {
                is ResultOf.Success -> {
                    _categories.postValue(response.data.data)
                    response.data.data.forEachIndexed { index, item ->
                        if (index < 1)
                            getItemByCategoryId(
                                id = item.id,
                                title = item.nameKh,
                                controller = controller
                            )
                        else
                            return@forEachIndexed
                    }
                }

                is ResultOf.Error -> {
                    println("Error .... ${response.exception}")
                }
            }
        }
    }

    private fun getItemByCategoryId(id: Int, title: String, controller: CategoryController) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getItem(id = id)) {

                is ResultOf.Success -> {
                    val item = CategoryAndItem(
                        categoryName = title,
                        items = response.data.data
                    )
                    tempCategoryAndItem.add(item)
                    categoriesAndItems.postValue(tempCategoryAndItem)
                    controller.requestModelBuild()
                }

                is ResultOf.Error -> {
                    println("error get item ${response.exception}")
                }
            }
        }

    }
}