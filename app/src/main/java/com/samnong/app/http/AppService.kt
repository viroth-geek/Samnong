package com.samnong.app.http

import com.samnong.app.model.BaseClass
import com.samnong.app.model.CategoryElement
import com.samnong.app.model.Item
import com.samnong.app.model.PlaceHolder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {
    @GET("categories")
    suspend fun getCategory(): Response<BaseClass<ArrayList<CategoryElement>>>

    @GET("categories/{id}/products")
    suspend fun getItem(
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BaseClass<ArrayList<Item>>>

    @GET("posts")
    suspend fun getPlaceHolder(): Response<ArrayList<PlaceHolder>>
}