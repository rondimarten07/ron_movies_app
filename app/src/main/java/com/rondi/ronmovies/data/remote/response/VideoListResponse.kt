package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class VideoListResponse(
    @SerializedName("results")
    val results: List<VideoResponse>
)

data class VideoResponse(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("type")
    val type: String
)