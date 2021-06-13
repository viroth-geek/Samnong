package com.samnong.app.view.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.epoxy.controller.DetailController
import com.samnong.app.model.Detail
import com.samnong.app.model.Item
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    var relatedProducts: ArrayList<Item> = ArrayList()
    var detail: Detail? = null
    var context: Context? = null

    fun getDetail(id: Int, controller: DetailController, context: Context) {
        this.context = context
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getDetail(id = id)) {
                is ResultOf.Success -> {
                    detail = response.data.data
                    getRelatedProduct(id = id, controller = controller)
                }
                is ResultOf.Error -> {
                    println("Error ${response.exception}")
                }
            }
        }
    }

    private fun getRelatedProduct(id: Int, controller: DetailController) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = SamnongApp.mainRepository.getRelatedItem(id = id)) {
                is ResultOf.Success -> {
                    relatedProducts.addAll(response.data.data)
                    controller.requestModelBuild()
                }
                is ResultOf.Error -> {
                    println("Error ${response.exception}")
                }
            }
        }
    }

}