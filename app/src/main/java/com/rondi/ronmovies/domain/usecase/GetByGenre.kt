package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetByGenre @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) {
    operator fun invoke(
        detailType: Detail,
        genreId: Int,
        page: Int
    ): Flow<Resource<Any>> = flow {
        emit(
            when (detailType) {
                Detail.MOVIE -> movieRepository.getMoviesByGenre(genreId, page)
                Detail.TV -> tvRepository.getTvsByGenre(genreId, page)
                else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
            }
        )
    }
}