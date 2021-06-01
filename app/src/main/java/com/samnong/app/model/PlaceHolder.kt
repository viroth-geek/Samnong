package com.samnong.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceHolder (
    @SerializedName("userId")
    val userID: Long,
    val id: Long,
    val title: String,
    val body: String
)
