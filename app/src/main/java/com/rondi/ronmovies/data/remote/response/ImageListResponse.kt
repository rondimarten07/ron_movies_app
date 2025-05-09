package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class ImageListResponse(
    @SerializedName("backdrops")
    val backdrops: List<ImageResponse>?,
    @SerializedName("logos")
    val logos: List<ImageResponse>?,
    @SerializedName("posters")
    val posters: List<ImageResponse>?,
    @SerializedName("profiles")
    val profiles: List<ImageResponse>?,
    @SerializedName("stills")
    val stills: List<ImageResponse>?,
)

data class ImageResponse(
    @SerializedName("file_path")
    val filePath: String
)