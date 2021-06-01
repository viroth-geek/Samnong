package com.samnong.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class Item(
    val id: Long,

    @SerializedName("client_id")
    val clientID: Long,

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

    @SerializedName("vdo_url")
    val vdoURL: String? = null,

    val ordering: String,
    val prices: ArrayList<Price>
)

data class Price(
    val id: Long,
    val variation: String,
    val price: Any,
    @SerializedName("discount_dollar")
    val discountDollar: Long,
    val uom: Uom
)

@Serializable
data class Uom(
    val id: Long,

    @SerializedName("name_en")
    val nameEn: String,

    @SerializedName("name_kh")
    val nameKh: String,

    @SerializedName("name_ch")
    val nameCh: String
)