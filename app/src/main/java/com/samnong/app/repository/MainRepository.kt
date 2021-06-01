package com.samnong.app.repository

import android.content.Context
import com.samnong.app.http.AppService
import com.samnong.app.model.*
import com.samnong.app.utils.ResultOf
import retrofit2.Response

class MainRepository(
    private val context: Context? = null,
    private val appService: AppService? = null
) {
    suspend fun getCategory(): ResultOf<BaseClass<ArrayList<CategoryElement>>> {
        return safeApiCall {
            appService!!.getCategory()
        }
    }

    suspend fun getItem(id: Int, page: Int = 1, limit: Int = 15) : ResultOf<BaseClass<ArrayList<Item>>> {
        return safeApiCall {
            appService!!.getItem(id = id, page = page, limit = limit)
        }
    }

    suspend fun getPlaceHolder() : ResultOf<ArrayList<PlaceHolder>> {
        return safeApiCall {
            appService!!.getPlaceHolder()
        }
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {
                ResultOf.Error(response.errorBody()?.toString() ?: "Something went wrong")
            }

        } catch (e: Exception) {
            ResultOf.Error(e.message ?: "Internet error runs")
        }
    }
}