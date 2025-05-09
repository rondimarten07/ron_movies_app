package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.PersonRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetails @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val personRepository: PersonRepository
) {
    operator fun invoke(
        detailType: Detail,
        id: Int,
        seasonNumber: Int? = null,
        episodeNumber: Int? = null
    ): Flow<Resource<Any>> = flow {
        emit(
            when (detailType) {
                Detail.MOVIE -> movieRepository.getMovieDetails(id)
                Detail.TV -> tvRepository.getTvDetails(id)
                Detail.TV_SEASON -> tvRepository.getSeasonDetails(id, seasonNumber!!)
                Detail.TV_EPISODE -> tvRepository.getEpisodeDetails(id, seasonNumber!!, episodeNumber!!)
                Detail.PERSON -> personRepository.getPersonDetails(id)
            }
        )
    }
}