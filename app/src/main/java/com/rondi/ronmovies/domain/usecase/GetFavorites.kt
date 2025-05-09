package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) {
    operator fun invoke(detailType: Detail): Flow<List<Any>> = flow {
        emit(
            when (detailType) {
                Detail.MOVIE -> movieRepository.getFavoriteMovies()
                Detail.TV -> tvRepository.getFavoriteTvs()
                else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
            }
        )
    }
}