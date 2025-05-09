package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class PersonDetailResponse(
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("deathday")
    val deathday: String?,
    @SerializedName("external_ids")
    val externalIds: ExternalResponse,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: ImageListResponse,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("movie_credits")
    val movieCredits: MovieCreditsResponse,
    @SerializedName("name")
    val name: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("tv_credits")
    val tvCredits: TvCreditsResponse
)

data class MovieCreditsResponse(
    @SerializedName("cast")
    val cast: List<MovieResponse>,
    @SerializedName("crew")
    val crew: List<MovieResponse>
)

data class TvCreditsResponse(
    @SerializedName("cast")
    val cast: List<TvResponse>,
    @SerializedName("crew")
    val crew: List<TvResponse>
)