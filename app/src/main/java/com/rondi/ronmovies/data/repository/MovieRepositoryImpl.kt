package com.rondi.ronmovies.data.repository

import com.rondi.ronmovies.data.local.dao.MovieDao
import com.rondi.ronmovies.data.mapper.toFavoriteMovie
import com.rondi.ronmovies.data.mapper.toFavoriteMovieEntity
import com.rondi.ronmovies.data.mapper.toMovieDetail
import com.rondi.ronmovies.data.mapper.toMovieList
import com.rondi.ronmovies.data.mapper.toVideoList
import com.rondi.ronmovies.data.remote.api.MovieApi
import com.rondi.ronmovies.domain.model.FavoriteMovie
import com.rondi.ronmovies.domain.model.MovieDetail
import com.rondi.ronmovies.domain.model.MovieList
import com.rondi.ronmovies.domain.model.VideoList
import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.util.Resource
import com.rondi.ronmovies.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val safeApiCall: SafeApiCall,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getMovieList(listId: String, page: Int, region: String?): Resource<MovieList> = safeApiCall.execute {
        api.getMovieList(listId, page, region).toMovieList()
    }

    override suspend fun getTrendingMovies(): Resource<MovieList> = safeApiCall.execute {
        api.getTrendingMovies().toMovieList()
    }

    override suspend fun getTrendingMovieTrailers(movieId: Int): Resource<VideoList> = safeApiCall.execute {
        api.getTrendingMovieTrailers(movieId).toVideoList()
    }

    override suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<MovieList> = safeApiCall.execute {
        api.getMoviesByGenre(genreId, page).toMovieList()
    }

    override suspend fun getMovieSearchResults(query: String, page: Int): Resource<MovieList> = safeApiCall.execute {
        api.getMovieSearchResults(query, page).toMovieList()
    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetail> = safeApiCall.execute {
        api.getMovieDetails(movieId).toMovieDetail()
    }

    override suspend fun getFavoriteMovies(): List<FavoriteMovie> = dao.getAllMovies().map { it.toFavoriteMovie() }

    override suspend fun movieExists(movieId: Int): Boolean = dao.movieExists(movieId)

    override suspend fun insertMovie(movie: FavoriteMovie) {
        dao.insertMovie(movie.toFavoriteMovieEntity())
    }

    override suspend fun deleteMovie(movie: FavoriteMovie) {
        dao.deleteMovie(movie.toFavoriteMovieEntity())
    }
}