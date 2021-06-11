package com.samnong.app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samnong.app.epoxy.controller.CategoryController
import com.samnong.app.view.home.HomeViewModel

class ViewModelFactory(private var controller: CategoryController): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(controller) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}