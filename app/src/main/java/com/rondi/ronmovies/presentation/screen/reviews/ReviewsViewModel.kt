package com.rondi.ronmovies.presentation.screen.reviews

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rondi.ronmovies.domain.model.ReviewList
import com.rondi.ronmovies.domain.usecase.GetReviews
import com.rondi.ronmovies.presentation.base.BaseViewModel
import com.rondi.ronmovies.presentation.screen.UiState
import com.rondi.ronmovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getReviews: GetReviews,
) : BaseViewModel() {

    private val _results = MutableStateFlow(setOf<Any>())
    val results get() = _results.asStateFlow()

    private var seriesId: Int? = null

    private var page = 1

    private fun fetchReviewList() {
        viewModelScope.launch {
            getReviews(
                seriesId = seriesId!!,
                page = page,
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _results.value = (_results.value + (response.data as ReviewList).results).toSet()

                        _uiState.value = UiState.successState()
                    }

                    is Resource.Error -> {
                        _uiState.value = UiState.errorState(errorText = response.message)
                    }
                }
            }
        }
    }

    fun onLoadMore(type: Any?) {
        _uiState.value = UiState.loadingState()
        page++
        fetchReviewList()
    }

    fun initFetchReviews(
        seriesId: Int?
    ) {
        this.seriesId = seriesId
        fetchReviewList()
    }
}