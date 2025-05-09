package com.rondi.ronmovies.data.mapper

import com.rondi.ronmovies.data.local.entity.FavoriteMovieEntity
import com.rondi.ronmovies.data.local.entity.FavoriteTvEntity
import com.rondi.ronmovies.domain.model.FavoriteMovie
import com.rondi.ronmovies.domain.model.FavoriteTv

fun FavoriteMovie.toFavoriteMovieEntity(): FavoriteMovieEntity = FavoriteMovieEntity(
    id = id,
    posterPath = posterPath,
    releaseDate = releaseDate,
    runtime = runtime,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
    date = date
)

fun FavoriteMovieEntity.toFavoriteMovie(): FavoriteMovie = FavoriteMovie(
    id = id,
    posterPath = posterPath,
    releaseDate = releaseDate,
    runtime = runtime,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
    date = date
)

fun FavoriteTv.toFavoriteTvEntity(): FavoriteTvEntity = FavoriteTvEntity(
    id = id,
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate,
    name = name,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    date = date
)

fun FavoriteTvEntity.toFavoriteTv(): FavoriteTv = FavoriteTv(
    id = id,
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate,
    name = name,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    date = date
)