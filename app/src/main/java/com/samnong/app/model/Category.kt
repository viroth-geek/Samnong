package com.samnong.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerializedName("data")
    val categories: ArrayList<CategoryElement>
)

@Serializable
data class CategoryElement(
    val id: Int,
    @SerializedName("item_type")
    val itemType: String,

    @SerializedName("name_en")
    val nameEn: String,

    @SerializedName("name_kh")
    val nameKh: String,

    @SerializedName("name_ch")
    val nameCh: String,

    val icon: String
)