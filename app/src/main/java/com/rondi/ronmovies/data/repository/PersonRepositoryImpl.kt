package com.rondi.ronmovies.data.repository

import com.rondi.ronmovies.data.mapper.toPersonDetail
import com.rondi.ronmovies.data.mapper.toPersonList
import com.rondi.ronmovies.data.remote.api.PersonApi
import com.rondi.ronmovies.domain.model.PersonDetail
import com.rondi.ronmovies.domain.model.PersonList
import com.rondi.ronmovies.domain.repository.PersonRepository
import com.rondi.ronmovies.util.Resource
import com.rondi.ronmovies.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    private val api: PersonApi,
    private val safeApiCall: SafeApiCall
) : PersonRepository {
    override suspend fun getPersonSearchResults(query: String, page: Int): Resource<PersonList> = safeApiCall.execute {
        api.getPersonSearchResults(query, page).toPersonList()
    }

    override suspend fun getPersonDetails(personId: Int): Resource<PersonDetail> = safeApiCall.execute {
        api.getPersonDetails(personId).toPersonDetail()
    }
}