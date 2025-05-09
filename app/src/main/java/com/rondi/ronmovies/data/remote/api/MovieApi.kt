package com.rondi.ronmovies.data.remote.api

import com.rondi.ronmovies.data.remote.response.MovieDetailResponse
import com.rondi.ronmovies.data.remote.response.MovieListResponse
import com.rondi.ronmovies.data.remote.response.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{list_id}")
    suspend fun getMovieList(
        @Path("list_id") listId: String,
        @Query("page") page: Int,
        @Query("region") region: String?
    ): MovieListResponse

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): MovieListResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getTrendingMovieTrailers(
        @Path("movie_id") movieId: Int
    ): VideoListResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("search/movie")
    suspend fun getMovieSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/{movie_id}?&append_to_response=credits,videos,images,recommendations,external_ids")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailResponse
}