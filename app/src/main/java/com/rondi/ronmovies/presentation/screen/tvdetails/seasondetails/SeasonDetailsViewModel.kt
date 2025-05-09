package com.rondi.ronmovies.presentation.screen.tvdetails.seasondetails

import androidx.lifecycle.viewModelScope
import com.rondi.ronmovies.domain.model.SeasonDetail
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
class SeasonDetailsViewModel @Inject constructor(private val getDetails: GetDetails) : BaseViewModel() {

    private val _details = MutableStateFlow(SeasonDetail.empty)
    val details get() = _details.asStateFlow()

    private fun fetchSeasonDetails(seasonNumber: Int) {
        viewModelScope.launch {
            getDetails(Detail.TV_SEASON, detailId, seasonNumber).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _details.value = response.data as SeasonDetail
                        _uiState.value = UiState.successState()
                    }

                    is Resource.Error -> {
                        _uiState.value = UiState.errorState(errorText = response.message)
                    }
                }
            }
        }
    }

    fun initRequest(tvId: Int, seasonNumber: Int) {
        detailId = tvId
        fetchSeasonDetails(seasonNumber)
    }
}