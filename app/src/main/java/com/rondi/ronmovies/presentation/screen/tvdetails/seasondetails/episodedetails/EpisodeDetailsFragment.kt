package com.rondi.ronmovies.presentation.screen.tvdetails.seasondetails.episodedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.FragmentEpisodeDetailsBinding
import com.rondi.ronmovies.presentation.adapter.ImageAdapter
import com.rondi.ronmovies.presentation.adapter.PersonAdapter
import com.rondi.ronmovies.presentation.adapter.VideoAdapter
import com.rondi.ronmovies.presentation.base.BaseFragment
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.playYouTubeVideo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailsFragment : BaseFragment<FragmentEpisodeDetailsBinding>(R.layout.fragment_episode_details) {

    override val viewModel: EpisodeDetailsViewModel by viewModels()

    val adapterVideos = VideoAdapter { playYouTubeVideo(it) }
    val adapterCast = PersonAdapter(isCast = true)
    val adapterGuestStars = PersonAdapter(isCast = true)
    val adapterImages = ImageAdapter()

    val seasonNumber by lazy { arguments?.getInt(Constants.SEASON_NUMBER)!! }
    val episodeNumber by lazy { arguments?.getInt(Constants.EPISODE_NUMBER)!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manageRecyclerViewAdapterLifecycle(
            binding.rvVideos,
            binding.rvCast,
            binding.rvGuestStars,
            binding.rvImages
        )

        viewModel.initRequest(detailId, seasonNumber, episodeNumber)

        collectFlows(
            listOf(
                ::collectDetails,
                ::collectUiState
            )
        )
    }

    private suspend fun collectDetails() {
        viewModel.details.collect { details ->
            adapterVideos.submitList(details.videos.filterVideos())
            adapterCast.submitList(details.credits.cast)
            adapterGuestStars.submitList(details.credits.guestStars)
            adapterImages.submitList(details.images.stills)
        }
    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { state ->
            if (state.isError) showSnackbar(message = state.errorText!!, actionText = getString(R.string.button_retry)) {
                viewModel.retryConnection {
                    viewModel.initRequest(detailId, seasonNumber, episodeNumber)
                }
            }
        }
    }
}