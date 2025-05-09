package com.rondi.ronmovies.domain.repository

import com.rondi.ronmovies.domain.model.EpisodeDetail
import com.rondi.ronmovies.domain.model.FavoriteTv
import com.rondi.ronmovies.domain.model.ReviewList
import com.rondi.ronmovies.domain.model.SeasonDetail
import com.rondi.ronmovies.domain.model.TvDetail
import com.rondi.ronmovies.domain.model.TvList
import com.rondi.ronmovies.domain.model.VideoList
import com.rondi.ronmovies.util.Resource

interface TvRepository {
    suspend fun getTvList(listId: String, page: Int): Resource<TvList>
    suspend fun getTrendingTvs(): Resource<TvList>
    suspend fun getTrendingTvTrailers(tvId: Int): Resource<VideoList>
    suspend fun getTvsByGenre(genreId: Int, page: Int): Resource<TvList>
    suspend fun getTvSearchResults(query: String, page: Int): Resource<TvList>
    suspend fun getTvDetails(tvId: Int): Resource<TvDetail>
    suspend fun getSeasonDetails(tvId: Int, seasonNumber: Int): Resource<SeasonDetail>
    suspend fun getEpisodeDetails(tvId: Int, seasonNumber: Int, episodeNumber: Int): Resource<EpisodeDetail>
    suspend fun getReviews(seriesId: Int, page: Int): Resource<ReviewList>
    suspend fun getFavoriteTvs(): List<FavoriteTv>
    suspend fun tvExists(tvId: Int): Boolean
    suspend fun insertTv(tv: FavoriteTv)
    suspend fun deleteTv(tv: FavoriteTv)
}