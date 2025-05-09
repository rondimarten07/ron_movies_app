package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class EpisodeDetailResponse(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("credits")
    val credits: CreditsResponse,
    @SerializedName("images")
    val images: ImageListResponse,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("still_path")
    val stillPath: String?,
    @SerializedName("videos")
    val videos: VideoListResponse,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)
