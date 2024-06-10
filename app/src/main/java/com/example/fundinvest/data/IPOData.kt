package com.example.fundinvest.data

import com.google.gson.annotations.SerializedName

data class IPOData(
    @SerializedName("date") val date: String,
    @SerializedName("daa") val daa: String,
    @SerializedName("company") val company: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("exchange") val exchange: String,
    @SerializedName("actions") val actions: String,
    @SerializedName("shares") val shares: Long?,
    @SerializedName("priceRange") val priceRange: String?,
    @SerializedName("marketCap") val marketCap: Long?
)