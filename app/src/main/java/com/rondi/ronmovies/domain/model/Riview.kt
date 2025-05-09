package com.rondi.ronmovies.domain.model

data class ReviewList(
    val results: List<Review>,
    val totalResults: Int
) {
    companion object {
        val empty = ReviewList(
            results = emptyList(),
            totalResults = 0
        )
    }
}

data class Review(
    val author: String?,
    val authorDetails: AuthorDetails?,
    val content: String?,
    val createdAt: String?,
    val id: String?,
    val updatedAt: String?,
    val url: String?
)

data class AuthorDetails(
    val name: String?,
    val username: String?,
    val avatarPath: String?,
    val rating: Int?
)
