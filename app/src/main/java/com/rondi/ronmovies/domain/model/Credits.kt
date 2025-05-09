package com.rondi.ronmovies.domain.model

data class Credits(
    val cast: List<Person>,
    val crew: List<Person>,
    val guestStars: List<Person>?
) {
    fun getDirector(): String = crew.find { it.job == "Director" }?.name ?: ""
    fun getWriters(): String = crew.filter { it.department == "Writing" }.joinToString { it.name + " (${it.job})" }

    companion object {
        val empty = Credits(
            cast = emptyList(),
            crew = emptyList(),
            guestStars = null
        )
    }
}