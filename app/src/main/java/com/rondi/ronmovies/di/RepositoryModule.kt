package com.rondi.ronmovies.di

import com.rondi.ronmovies.data.repository.MovieRepositoryImpl
import com.rondi.ronmovies.data.repository.PersonRepositoryImpl
import com.rondi.ronmovies.data.repository.TvRepositoryImpl
import com.rondi.ronmovies.domain.repository.MovieRepository
import com.rondi.ronmovies.domain.repository.PersonRepository
import com.rondi.ronmovies.domain.repository.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindTvRepository(repository: TvRepositoryImpl): TvRepository

    @Binds
    abstract fun bindPersonRepository(repository: PersonRepositoryImpl): PersonRepository
}