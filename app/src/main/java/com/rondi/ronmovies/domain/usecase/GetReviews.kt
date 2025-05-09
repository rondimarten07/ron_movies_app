package com.rondi.ronmovies.domain.usecase

import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReviews @Inject constructor(
    private val tvRepository: TvRepository
) {
    operator fun invoke(
        seriesId: Int? = null,
        page: Int? = null,
    ): Flow<Resource<Any>> = flow {
        emit(tvRepository.getReviews(seriesId!!, page!!))
    }
}