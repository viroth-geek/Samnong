package com.samnong.app.http

import com.samnong.app.model.*
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

    @GET("products/{id}/related")
    suspend fun getRelatedItem(
        @Path("id") id: Int,
    ): Response<BaseClass<ArrayList<Item>>>

    @GET("products/{id}")
    suspend fun getDetail(
        @Path("id") id: Int
    ): Response<BaseClass<Detail>>
}