package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.model.VideoList
import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendingVideos @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) {
    operator fun invoke(
        detailType: Detail,
        id: Int
    ): Flow<Resource<VideoList>> = flow {
        emit(
            when (detailType) {
                Detail.MOVIE -> movieRepository.getTrendingMovieTrailers(id)
                Detail.TV -> tvRepository.getTrendingTvTrailers(id)
                else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
            }
        )
    }
}