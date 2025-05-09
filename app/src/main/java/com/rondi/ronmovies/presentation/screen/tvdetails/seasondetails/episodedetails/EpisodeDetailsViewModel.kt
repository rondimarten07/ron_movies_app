package com.rondi.ronmovies.presentation.screen.tvdetails.seasondetails.episodedetails

import androidx.lifecycle.viewModelScope
import com.rondi.ronmovies.domain.model.EpisodeDetail
import com.rondi.ronmovies.domain.usecase.GetDetails
import com.rondi.ronmovies.presentation.base.BaseViewModel
import com.rondi.ronmovies.presentation.screen.UiState
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(private val getDetails: GetDetails) : BaseViewModel() {

    private val _details = MutableStateFlow(EpisodeDetail.empty)
    val details get() = _details.asStateFlow()

    private fun fetchEpisodeDetails(seasonNumber: Int, episodeNumber: Int) {
        viewModelScope.launch {
            getDetails(Detail.TV_EPISODE, detailId, seasonNumber, episodeNumber).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _details.value = response.data as EpisodeDetail
                        _uiState.value = UiState.successState()
                    }

                    is Resource.Error -> {
                        _uiState.value = UiState.errorState(errorText = response.message)
                    }
                }
            }
        }
    }

    fun initRequest(tvId: Int, seasonNumber: Int, episodeNumber: Int) {
        detailId = tvId
        fetchEpisodeDetails(seasonNumber, episodeNumber)
    }
}