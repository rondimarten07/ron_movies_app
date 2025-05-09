package com.rondi.ronmovies.presentation.screen.favorites.favoritemovies

import androidx.lifecycle.viewModelScope
import com.rondi.ronmovies.domain.model.FavoriteMovie
import com.rondi.ronmovies.domain.usecase.AddFavorite
import com.rondi.ronmovies.domain.usecase.DeleteFavorite
import com.rondi.ronmovies.domain.usecase.GetFavorites
import com.rondi.ronmovies.presentation.base.BaseViewModel
import com.rondi.ronmovies.util.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val getFavorites: GetFavorites,
    private val deleteFavorite: DeleteFavorite,
    private val addFavorite: AddFavorite
) : BaseViewModel() {

    private val _favoriteMovies = MutableStateFlow(emptyList<FavoriteMovie>())
    val favoriteMovies get() = _favoriteMovies.asStateFlow()

    fun fetchFavoriteMovies() {
        viewModelScope.launch {
            getFavorites(Detail.MOVIE).collect {
                _favoriteMovies.value = it as List<FavoriteMovie>
            }
        }
    }

    fun removeMovieFromFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            deleteFavorite(detailType = Detail.MOVIE, movie = movie)
            fetchFavoriteMovies()
        }
    }

    fun addMovieToFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            addFavorite(detailType = Detail.MOVIE, movie = movie)
            fetchFavoriteMovies()
        }
    }

    init {
        fetchFavoriteMovies()
    }
}