package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewListResponse(
    @SerializedName("results")
    val results: List<ReviewResponse>,
    @SerializedName("total_results")
    val totalResults: Int
)

data class ReviewResponse(
    @SerializedName("author")
    val author: String? = null,

    @SerializedName("author_details")
    val authorDetails: AuthorDetailsResponse? = null,

    @SerializedName("content")
    val content: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("url")
    val url: String? = null
)

data class AuthorDetailsResponse(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("avatar_path")
    val avatarPath: String? = null,

    @SerializedName("rating")
    val rating: Int? = null
)
