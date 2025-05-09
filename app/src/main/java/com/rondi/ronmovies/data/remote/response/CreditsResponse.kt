package com.rondi.ronmovies.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<PersonResponse>,
    @SerializedName("crew")
    val crew: List<PersonResponse>,
    @SerializedName("guest_stars")
    val guestStars: List<PersonResponse>?
)