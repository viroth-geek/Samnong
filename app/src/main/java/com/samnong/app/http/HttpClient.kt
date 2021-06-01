package com.samnong.app.http

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.samnong.app.SamnongApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val httpClient = OkHttpClient
    .Builder()
    .addInterceptor(ChuckerInterceptor.Builder(SamnongApp.appContext).build())
    .build()

private val retrofit = Retrofit
    .Builder()
    .client(httpClient)
    .baseUrl("http://phsar-samnong.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val appService: AppService = retrofit.create()