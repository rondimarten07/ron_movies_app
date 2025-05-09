package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)