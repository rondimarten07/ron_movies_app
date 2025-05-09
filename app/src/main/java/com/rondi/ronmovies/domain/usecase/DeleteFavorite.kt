package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.model.FavoriteMovie
import com.rondi.ronmovies.domain.model.FavoriteTv
import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import javax.inject.Inject

class DeleteFavorite @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) {
    suspend operator fun invoke(
        detailType: Detail,
        movie: FavoriteMovie? = null,
        tv: FavoriteTv? = null
    ) {
        when (detailType) {
            Detail.MOVIE -> movieRepository.deleteMovie(movie!!)
            Detail.TV -> tvRepository.deleteTv(tv!!)
            else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
        }
    }
}