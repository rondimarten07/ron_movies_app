package com.rondi.ronmovies.data.repository

import com.rondi.ronmovies.data.local.dao.TvDao
import com.rondi.ronmovies.data.mapper.toEpisodeDetail
import com.rondi.ronmovies.data.mapper.toFavoriteTv
import com.rondi.ronmovies.data.mapper.toFavoriteTvEntity
import com.rondi.ronmovies.data.mapper.toReviewList
import com.rondi.ronmovies.data.mapper.toSeasonDetail
import com.rondi.ronmovies.data.mapper.toTvDetail
import com.rondi.ronmovies.data.mapper.toTvList
import com.rondi.ronmovies.data.mapper.toVideoList
import com.rondi.ronmovies.data.remote.api.TvApi
import com.rondi.ronmovies.domain.model.EpisodeDetail
import com.rondi.ronmovies.domain.model.FavoriteTv
import com.rondi.ronmovies.domain.model.ReviewList
import com.rondi.ronmovies.domain.model.SeasonDetail
import com.rondi.ronmovies.domain.model.TvDetail
import com.rondi.ronmovies.domain.model.TvList
import com.rondi.ronmovies.domain.model.VideoList
import com.rondi.ronmovies.domain.repository.TvRepository
import com.rondi.ronmovies.util.Resource
import com.rondi.ronmovies.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepositoryImpl @Inject constructor(
    private val api: TvApi,
    private val safeApiCall: SafeApiCall,
    private val dao: TvDao
) : TvRepository {
    override suspend fun getTvList(listId: String, page: Int): Resource<TvList> = safeApiCall.execute {
        api.getTvList(listId, page).toTvList()
    }

    override suspend fun getTrendingTvs(): Resource<TvList> = safeApiCall.execute {
        api.getTrendingTvs().toTvList()
    }

    override suspend fun getTrendingTvTrailers(tvId: Int): Resource<VideoList> = safeApiCall.execute {
        api.getTrendingTvTrailers(tvId).toVideoList()
    }

    override suspend fun getTvsByGenre(genreId: Int, page: Int): Resource<TvList> = safeApiCall.execute {
        api.getTvsByGenre(genreId, page).toTvList()
    }

    override suspend fun getTvSearchResults(query: String, page: Int): Resource<TvList> = safeApiCall.execute {
        api.getTvSearchResults(query, page).toTvList()
    }

    override suspend fun getTvDetails(tvId: Int): Resource<TvDetail> = safeApiCall.execute {
        api.getTvDetails(tvId).toTvDetail()
    }

    override suspend fun getSeasonDetails(tvId: Int, seasonNumber: Int): Resource<SeasonDetail> = safeApiCall.execute {
        api.getSeasonDetails(tvId, seasonNumber).toSeasonDetail()
    }

    override suspend fun getEpisodeDetails(tvId: Int, seasonNumber: Int, episodeNumber: Int): Resource<EpisodeDetail> = safeApiCall.execute {
        api.getEpisodeDetails(tvId, seasonNumber, episodeNumber).toEpisodeDetail()
    }

    override suspend fun getFavoriteTvs(): List<FavoriteTv> = dao.getAllTvs().map { it.toFavoriteTv() }

    override suspend fun tvExists(tvId: Int): Boolean = dao.tvExists(tvId)

    override suspend fun getReviews(seriesId: Int, page: Int): Resource<ReviewList> = safeApiCall.execute {
        api.getReviews(seriesId, page).toReviewList()
    }

    override suspend fun insertTv(tv: FavoriteTv) {
        dao.insertTv(tv.toFavoriteTvEntity())
    }

    override suspend fun deleteTv(tv: FavoriteTv) {
        dao.deleteTv(tv.toFavoriteTvEntity())
    }
}