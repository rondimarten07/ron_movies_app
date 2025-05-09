package com.rondi.ronmovies.presentation.screen.seeall

import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.rondi.ronmovies.domain.model.MovieList
import com.rondi.ronmovies.domain.model.PersonList
import com.rondi.ronmovies.domain.model.TvList
import com.rondi.ronmovies.domain.usecase.GetByGenre
import com.rondi.ronmovies.domain.usecase.GetList
import com.rondi.ronmovies.domain.usecase.GetSearchResults
import com.rondi.ronmovies.presentation.base.BaseViewModel
import com.rondi.ronmovies.presentation.screen.UiState
import com.rondi.ronmovies.util.Constants
import com.rondi.ronmovies.util.Content
import com.rondi.ronmovies.util.Detail
import com.rondi.ronmovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getList: GetList,
    private val getSearchResults: GetSearchResults,
    private val getByGenre: GetByGenre
) : BaseViewModel() {

    private val _results = MutableStateFlow(setOf<Any>())
    val results get() = _results.asStateFlow()

    private var contentType: Parcelable? = null
    private var detailType: Parcelable? = null
    private var genreId: Int? = null
    private var listId: String? = null
    private var region: String? = null

    private var page = 1

    private fun fetchList() {
        viewModelScope.launch {
            getList(
                detailType = detailType as Detail,
                listId = listId,
                page = page,
                region = region
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _results.value += when (detailType) {
                            Detail.MOVIE -> (response.data as MovieList).results
                            Detail.TV -> (response.data as TvList).results
                            else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
                        }

                        _uiState.value = UiState.successState()
                    }

                    is Resource.Error -> {
                        _uiState.value = UiState.errorState(errorText = response.message)
                    }
                }
            }
        }
    }

    private fun fetchSearchResults() {
        viewModelScope.launch {
            getSearchResults(
                detailType = detailType as Detail,
                query = listId!!,
                page = page
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _results.value += when (detailType) {
                            Detail.MOVIE -> (response.data as MovieList).results
                            Detail.TV -> (response.data as TvList).results
                            else -> (response.data as PersonList).results
                        }

                        _uiState.value = UiState.successState()
                    }

                    is Resource.Error -> {
                        _uiState.value = UiState.errorState(errorText = response.message)
                    }
                }
            }
        }
    }

    private fun fetchGenreResults() {
        viewModelScope.launch {
            getByGenre(
                detailType = detailType as Detail,
                genreId = genreId!!,
                page = page
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _results.value += when (detailType) {
                            Detail.MOVIE -> (response.data as MovieList).results
                            Detail.TV -> (response.data as TvList).results
                            else -> throw IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_DETAIL_TYPE)
                        }

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

        when (contentType) {
            Content.EXPLORE_LIST -> fetchList()
            Content.SEARCH -> fetchSearchResults()
            Content.GENRE -> fetchGenreResults()
        }
    }

    fun initRequest(
        contentType: Parcelable?,
        detailType: Parcelable?,
        genreId: Int?,
        listId: String?,
        region: String?
    ) {
        this.contentType = contentType
        this.detailType = detailType
        this.genreId = genreId
        this.listId = listId
        this.region = region

        when (contentType) {
            Content.EXPLORE_LIST -> fetchList()
            Content.SEARCH -> fetchSearchResults()
            Content.GENRE -> fetchGenreResults()
            else -> _uiState.value = UiState.successState()
        }
    }
}