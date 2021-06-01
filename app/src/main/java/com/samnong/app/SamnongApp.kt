package com.samnong.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.samnong.app.http.appService
import com.samnong.app.repository.MainRepository

class SamnongApp: Application() {
    companion object {
        lateinit var appContext: Context
            private set

        @SuppressLint("StaticFieldLeak")
        lateinit var mainRepository: MainRepository
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        mainRepository = MainRepository(context = this, appService = appService)
    }
}