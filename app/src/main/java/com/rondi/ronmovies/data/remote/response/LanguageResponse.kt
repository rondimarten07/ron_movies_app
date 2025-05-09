package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @SerializedName("english_name")
    val englishName: String
)