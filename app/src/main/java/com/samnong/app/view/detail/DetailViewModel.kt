package com.samnong.app.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samnong.app.SamnongApp
import com.samnong.app.model.Detail
import com.samnong.app.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ResponseCache

class DetailViewModel : ViewModel() {
    val detail: MutableLiveData<Detail> = MutableLiveData()
    var _detail:LiveData<Detail> = detail

    fun getDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val response = SamnongApp.mainRepository.getDetail(id = id)) {
                is ResultOf.Success -> {
                    println(response.data.data)
                }
                is ResultOf.Error -> {
                    println("Error ${response.exception}")
                }
            }
        }
    }
}