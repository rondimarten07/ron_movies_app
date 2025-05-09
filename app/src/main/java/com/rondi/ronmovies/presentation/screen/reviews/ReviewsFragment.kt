package com.rondi.ronmovies.presentation.screen.reviews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.FragmentReviewsBinding
import com.rondi.ronmovies.domain.model.Review
import com.rondi.ronmovies.presentation.adapter.ReviewAdapter
import com.rondi.ronmovies.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment : BaseFragment<FragmentReviewsBinding>(R.layout.fragment_reviews) {

    override val viewModel: ReviewsViewModel by viewModels()

    private val reviewAdapter by lazy { ReviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getList()

        binding.rvSeeAll.layoutManager = LinearLayoutManager(requireContext())

        manageRecyclerViewAdapterLifecycle(binding.rvSeeAll)
    }


    private fun getList() {
        binding.rvSeeAll.adapter = reviewAdapter

        viewModel.initFetchReviews(detailId)

        collectFlows(
            listOf(
                ::collectListResult,
                ::collectUiState
            )
        )
    }

    private suspend fun collectListResult() {
        viewModel.results.collect { reviews ->
            val reviewList = reviews as Set<Review>
            Log.d("HAIAIAHS", reviewList.toString())
            reviewAdapter.submitList(reviewList.toList())
        }
    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { state ->
            if (state.isError) showSnackbar(
                message = state.errorText!!,
                actionText = getString(R.string.button_retry)
            ) {
                viewModel.retryConnection {
                    viewModel.initFetchReviews(detailId)
                }
            }
        }
    }
}