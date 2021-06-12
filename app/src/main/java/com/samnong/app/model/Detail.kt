package com.samnong.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class Detail(
    val id: Long,

    @SerializedName("item_type")
    val itemType: String,

    @SerializedName("item_img")
    val itemImg: String,

    val code: String,

    @SerializedName("name_en")
    val nameEn: String,

    @SerializedName("name_kh")
    val nameKh: String,

    @SerializedName("name_ch")
    val nameCh: String,

    @SerializedName("description_en")
    val descriptionEn: String,

    @SerializedName("description_kh")
    val descriptionKh: String,

    @SerializedName("description_ch")
    val descriptionCh: String,

    @SerializedName("specification_en")
    val specificationEn: String? = null,

    @SerializedName("specification_kh")
    val specificationKh: String? = null,

    @SerializedName("specification_ch")
    val specificationCh: String? = null,

    @SerializedName("categories_id")
    val categoriesID: Long,

    @SerializedName("vdo_url")
    val vdoURL: String? = null,

    @SerializedName("view_cnt")
    val viewCnt: Long,

    val ordering: String,

    @SerializedName("is_favorite")
    val isFavorite: Boolean,

    val gallery: ArrayList<String>,
    val prices: ArrayList<Price>,
    val mobile: ArrayList<Mobile>
)

@Serializable
data class Mobile(
    val id: Long,

    @SerializedName("item_id")
    val itemID: Long,

    val phone: String,
    val ordering: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("deleted_at")
    val deletedAt: String? = null,

    val company: Company
)

@Serializable
data class Company(
    val id: Long,
    val name: String,
    val logo: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("deleted_at")
    val deletedAt: String? = null
)

