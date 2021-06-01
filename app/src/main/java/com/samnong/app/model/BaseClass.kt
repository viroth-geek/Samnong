package com.samnong.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseClass<T>(
    val message: String,
    val code: Int,
    @SerializedName("data")
    val data: T,
    val success: Boolean
)