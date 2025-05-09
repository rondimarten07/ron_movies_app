package com.rondi.ronmovies.di

import android.content.Context
import androidx.room.Room
import com.rondi.ronmovies.data.local.dao.MovieDao
import com.rondi.ronmovies.data.local.dao.TvDao
import com.rondi.ronmovies.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Singleton
    @Provides
    fun provideTvDao(appDatabase: AppDatabase): TvDao = appDatabase.tvDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie-app-db"
        ).build()
}