package com.rondi.ronmovies.presentation.screen.tvdetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.FragmentTvDetailsBinding
import com.rondi.ronmovies.presentation.adapter.ImageAdapter
import com.rondi.ronmovies.presentation.adapter.PersonAdapter
import com.rondi.ronmovies.presentation.adapter.SeasonAdapter
import com.rondi.ronmovies.presentation.adapter.TvAdapter
import com.rondi.ronmovies.presentation.adapter.VideoAdapter
import com.rondi.ronmovies.presentation.base.BaseFragment
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.playYouTubeVideo
import com.rondi.ronmovies.util.setGenreChips
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailsFragment : BaseFragment<FragmentTvDetailsBinding>(R.layout.fragment_tv_details) {

    override val viewModel: TvDetailsViewModel by viewModels()

    val adapterVideos = VideoAdapter { playYouTubeVideo(it) }
    val adapterCast = PersonAdapter(isCast = true)
    val adapterImages = ImageAdapter()
    val adapterRecommendations = TvAdapter()
    val adapterSeasons by lazy { SeasonAdapter(detailId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manageRecyclerViewAdapterLifecycle(
            binding.rvVideos,
            binding.rvCast,
            binding.rvImages,
            binding.rvSeasons,
            binding.rvRecommendations
        )

        viewModel.initRequest(detailId)

        binding.btnSeeReviews.setOnClickListener {
            val bundle = bundleOf(
                Constants.DETAIL_ID to detailId
            )
            findNavController().navigate(
                R.id.action_tvDetailsFragment_to_reviewsFragment,
                bundle
            )
        }

        collectFlows(
            listOf(
                ::collectDetails,
                ::collectUiState
            )
        )
    }

    private suspend fun collectDetails() {
        viewModel.details.collect { details ->
            binding.cgGenres.setGenreChips(details.genres, Detail.TV, backgroundColor)
            adapterVideos.submitList(details.videos.filterVideos())
            adapterCast.submitList(details.credits.cast)
            adapterImages.submitList(details.images.backdrops)
            adapterSeasons.submitList(details.seasons)
            adapterRecommendations.submitList(details.recommendations.results)
        }
    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { state ->
            if (state.isError) showSnackbar(message = state.errorText!!, actionText = getString(R.string.button_retry)) {
                viewModel.retryConnection {
                    viewModel.initRequest(id)
                }
            }
        }
    }
}