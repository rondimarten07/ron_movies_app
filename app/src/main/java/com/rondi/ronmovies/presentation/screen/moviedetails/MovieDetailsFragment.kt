package com.rondi.ronmovies.presentation.screen.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.FragmentMovieDetailsBinding
import com.rondi.ronmovies.presentation.adapter.ImageAdapter
import com.rondi.ronmovies.presentation.adapter.MovieAdapter
import com.rondi.ronmovies.presentation.adapter.PersonAdapter
import com.rondi.ronmovies.presentation.adapter.VideoAdapter
import com.rondi.ronmovies.presentation.base.BaseFragment
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.playYouTubeVideo
import com.rondi.ronmovies.util.setGenreChips
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding>(R.layout.fragment_movie_details) {

    override val viewModel: MovieDetailsViewModel by viewModels()

    val adapterVideos = VideoAdapter { playYouTubeVideo(it) }
    val adapterCast = PersonAdapter(isCast = true)
    val adapterImages = ImageAdapter()
    val adapterRecommendations = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manageRecyclerViewAdapterLifecycle(
            binding.rvVideos,
            binding.rvCast,
            binding.rvImages,
            binding.rvRecommendations
        )

        viewModel.initRequests(detailId)

        collectFlows(
            listOf(
                ::collectDetails,
                ::collectUiState
            )
        )
    }

    private suspend fun collectDetails() {
        viewModel.details.collect { details ->
            binding.cgGenres.setGenreChips(details.genres, Detail.MOVIE, backgroundColor)
            adapterVideos.submitList(details.videos.filterVideos())
            adapterCast.submitList(details.credits.cast)
            adapterImages.submitList(details.images.backdrops)
            adapterRecommendations.submitList(details.recommendations.results)
        }
    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { state ->
            if (state.isError) showSnackbar(
                message = state.errorText!!,
                actionText = getString(R.string.button_retry)
            ) {
                viewModel.retryConnection {
                    viewModel.initRequests(id)
                }
            }
        }
    }
}