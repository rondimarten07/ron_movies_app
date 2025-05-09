package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name")
    val name: String
)
