package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.PersonRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchResults @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val personRepository: PersonRepository
) {
    operator fun invoke(
        detailType: Detail,
        query: String,
        page: Int
    ): Flow<Resource<Any>> = flow {
        emit(
            when (detailType) {
                Detail.MOVIE -> movieRepository.getMovieSearchResults(query, page)
                Detail.TV -> tvRepository.getTvSearchResults(query, page)
                Detail.PERSON -> personRepository.getPersonSearchResults(query, page)
                else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
            }
        )
    }
}