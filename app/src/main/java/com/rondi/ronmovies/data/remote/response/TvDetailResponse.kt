package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvDetailResponse(
    @SerializedName("created_by")
    val createdBy: List<CreatorResponse>,
    @SerializedName("credits")
    val credits: CreditsResponse,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("external_ids")
    val externalIds: ExternalResponse,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: ImageListResponse,
    @SerializedName("in_production")
    val inProduction: Boolean,
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("networks")
    val networks: List<CompanyResponse>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeResponse?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<CompanyResponse>,
    @SerializedName("production_countries")
    val productionCountries: List<CountryResponse>,
    @SerializedName("recommendations")
    val recommendations: TvListResponse,
    @SerializedName("seasons")
    val seasons: List<SeasonResponse>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguageResponse>,
    @SerializedName("status")
    val status: String,
    @SerializedName("videos")
    val videos: VideoListResponse,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

data class CreatorResponse(
    @SerializedName("name")
    val name: String
)

data class SeasonResponse(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int
)