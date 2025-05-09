package com.rondi.ronmovies.data.remote.api

import com.rondi.ronmovies.data.remote.response.EpisodeDetailResponse
import com.rondi.ronmovies.data.remote.response.ReviewListResponse
import com.rondi.ronmovies.data.remote.response.SeasonDetailResponse
import com.rondi.ronmovies.data.remote.response.TvDetailResponse
import com.rondi.ronmovies.data.remote.response.TvListResponse
import com.rondi.ronmovies.data.remote.response.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApi {

    @GET("tv/{list_id}")
    suspend fun getTvList(
        @Path("list_id") listId: String,
        @Query("page") page: Int
    ): TvListResponse

    @GET("trending/tv/week")
    suspend fun getTrendingTvs(): TvListResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTrendingTvTrailers(
        @Path("tv_id") tvId: Int
    ): VideoListResponse

    @GET("discover/tv")
    suspend fun getTvsByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): TvListResponse

    @GET("search/tv")
    suspend fun getTvSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int
    ): TvListResponse

    @GET("tv/{tv_id}?&append_to_response=credits,videos,images,recommendations,external_ids")
    suspend fun getTvDetails(
        @Path("tv_id") tvId: Int
    ): TvDetailResponse

    @GET("tv/{tv_id}/season/{season_number}?&append_to_response=credits,videos,images")
    suspend fun getSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int
    ): SeasonDetailResponse

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}?&append_to_response=credits,videos,images")
    suspend fun getEpisodeDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): EpisodeDetailResponse

    @GET("tv/{series_id}/reviews")
    suspend fun getReviews(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int
    ): ReviewListResponse
}