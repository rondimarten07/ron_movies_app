package com.rondi.ronmovies.presentation.screen.favorites.favoritemovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.FragmentFavoriteMoviesBinding
import com.rondi.ronmovies.domain.model.FavoriteMovie
import com.rondi.ronmovies.presentation.adapter.FavoriteMovieAdapter
import com.rondi.ronmovies.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>(R.layout.fragment_favorite_movies) {

    override val viewModel: FavoriteMoviesViewModel by viewModels()

    val adapterFavorites = FavoriteMovieAdapter { removeMovie(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manageRecyclerViewAdapterLifecycle(binding.rvFavoriteMovies)
        collectFlows(listOf(::collectFavoriteMovies))
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFavoriteMovies()
    }

    private fun removeMovie(movie: FavoriteMovie) {
        viewModel.removeMovieFromFavorites(movie)
        showSnackbar(
            message = getString(R.string.snackbar_removed_item),
            actionText = getString(R.string.snackbar_action_undo),
            anchor = true
        ) {
            viewModel.addMovieToFavorites(movie)
        }
    }

    private suspend fun collectFavoriteMovies() {
        viewModel.favoriteMovies.collect { favoriteMovies ->
            adapterFavorites.submitList(favoriteMovies)
        }
    }
}