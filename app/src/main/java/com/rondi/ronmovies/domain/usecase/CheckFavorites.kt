package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import javax.inject.Inject

class CheckFavorites @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) {
    suspend operator fun invoke(
        detailType: Detail,
        id: Int
    ): Boolean = when (detailType) {
        Detail.MOVIE -> movieRepository.movieExists(id)
        Detail.TV -> tvRepository.tvExists(id)
        else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
    }
}