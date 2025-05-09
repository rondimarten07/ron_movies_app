package com.rondi.ronmovies.data.remote.api

import com.rondi.ronmovies.data.remote.response.PersonDetailResponse
import com.rondi.ronmovies.data.remote.response.PersonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {

    @GET("search/person")
    suspend fun getPersonSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int
    ): PersonListResponse

    @GET("person/{person_id}?&append_to_response=images,movie_credits,tv_credits,external_ids")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int
    ): PersonDetailResponse
}