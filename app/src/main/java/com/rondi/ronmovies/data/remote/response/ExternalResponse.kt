package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class ExternalResponse(
    @SerializedName("facebook_id")
    val facebookId: String?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("instagram_id")
    val instagramId: String?,
    @SerializedName("twitter_id")
    val twitterId: String?
)