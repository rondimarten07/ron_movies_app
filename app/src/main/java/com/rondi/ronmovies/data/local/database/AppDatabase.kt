package com.rondi.ronmovies.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rondi.ronmovies.data.local.dao.MovieDao
import com.rondi.ronmovies.data.local.dao.TvDao
import com.rondi.ronmovies.data.local.entity.FavoriteMovieEntity
import com.rondi.ronmovies.data.local.entity.FavoriteTvEntity

@Database(entities = [FavoriteMovieEntity::class, FavoriteTvEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}