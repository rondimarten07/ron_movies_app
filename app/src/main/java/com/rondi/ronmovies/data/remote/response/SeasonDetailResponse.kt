package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class SeasonDetailResponse(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("credits")
    val credits: CreditsResponse,
    @SerializedName("episodes")
    val episodes: List<EpisodeResponse>,
    @SerializedName("images")
    val images: ImageListResponse,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("videos")
    val videos: VideoListResponse
)

data class EpisodeResponse(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_number")
    val episodeNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("still_path")
    val stillPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)