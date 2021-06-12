package com.samnong.app.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.model.CategoryElement
import com.samnong.app.model.Content
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList

class HomeViewModel : ViewModel() {

    private val _categories: MutableLiveData<ArrayList<CategoryElement>> = MutableLiveData(ArrayList())
    var categories: LiveData<ArrayList<CategoryElement>> = _categories

    val showDetail: MutableLiveData<Boolean> = MutableLiveData(false)
    val content : MutableLiveData<List<Content>> = MutableLiveData<List<Content>>()
    private var tempContent: CopyOnWriteArrayList<Content> = CopyOnWriteArrayList()

    fun getCategory() {
        if (categories.value?.isNotEmpty() == true) {
            content.postValue(tempContent)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getCategory()) {
                is ResultOf.Success -> {
                    _categories.postValue(response.data.data)
                    response.data.data.forEachIndexed { index, item ->
                        if(index < 30) {
                            getItemByCategoryId(id = item.id, title = item.nameKh)
                        }
                        else {
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
                    val banner = Content.Banner(title)
                    val carousel = Content.Carousel(list = response.data.data, id = title)
                    tempContent.add(banner)
                    tempContent.add(carousel)
                    content.postValue(tempContent)
                }
                is ResultOf.Error -> {
                    println("error get item ${response.exception}")
                }
            }
        }
    }
}