package com.rondi.ronmovies.domain.repository

import com.rondi.ronmovies.domain.model.FavoriteMovie
import com.rondi.ronmovies.domain.model.MovieDetail
import com.rondi.ronmovies.domain.model.MovieList
import com.rondi.ronmovies.domain.model.VideoList
import com.rondi.ronmovies.util.Resource

interface MovieRepository {
    suspend fun getMovieList(listId: String, page: Int, region: String?): Resource<MovieList>
    suspend fun getTrendingMovies(): Resource<MovieList>
    suspend fun getTrendingMovieTrailers(movieId: Int): Resource<VideoList>
    suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<MovieList>
    suspend fun getMovieSearchResults(query: String, page: Int): Resource<MovieList>
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetail>
    suspend fun getFavoriteMovies(): List<FavoriteMovie>
    suspend fun movieExists(movieId: Int): Boolean
    suspend fun insertMovie(movie: FavoriteMovie)
    suspend fun deleteMovie(movie: FavoriteMovie)
}