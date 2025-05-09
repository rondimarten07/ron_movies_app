package com.rondi.ronmovies.domain.repository

import com.rondi.ronmovies.domain.model.PersonDetail
import com.rondi.ronmovies.domain.model.PersonList
import com.rondi.ronmovies.util.Resource

interface PersonRepository {
    suspend fun getPersonSearchResults(query: String, page: Int): Resource<PersonList>
    suspend fun getPersonDetails(personId: Int): Resource<PersonDetail>
}