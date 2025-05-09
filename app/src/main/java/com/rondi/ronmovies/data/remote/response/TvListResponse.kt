package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvListResponse(
    @SerializedName("results")
    val results: List<TvResponse>,
    @SerializedName("total_results")
    val totalResults: Int
)

data class TvResponse(
    @SerializedName("character")
    val character: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double
)