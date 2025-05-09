package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("credits")
    val credits: CreditsResponse,
    @SerializedName("external_ids")
    val externalIds: ExternalResponse,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: ImageListResponse,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<CompanyResponse>,
    @SerializedName("production_countries")
    val productionCountries: List<CountryResponse>,
    @SerializedName("recommendations")
    val recommendations: MovieListResponse,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("revenue")
    val revenue: Long,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguageResponse>,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("videos")
    val videos: VideoListResponse,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)