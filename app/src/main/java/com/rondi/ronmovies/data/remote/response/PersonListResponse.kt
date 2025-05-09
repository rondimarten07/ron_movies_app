package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class PersonListResponse(
    @SerializedName("results")
    val results: List<PersonResponse>,
    @SerializedName("total_results")
    val totalResults: Int
)

data class PersonResponse(
    @SerializedName("character")
    val character: String?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: String?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)