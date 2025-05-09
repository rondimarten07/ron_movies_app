package com.rondi.ronmovies.data.mapper

import com.rondi.ronmovies.data.remote.response.AuthorDetailsResponse
import com.rondi.ronmovies.data.remote.response.CompanyResponse
import com.rondi.ronmovies.data.remote.response.CountryResponse
import com.rondi.ronmovies.data.remote.response.CreatorResponse
import com.rondi.ronmovies.data.remote.response.CreditsResponse
import com.rondi.ronmovies.data.remote.response.EpisodeDetailResponse
import com.rondi.ronmovies.data.remote.response.EpisodeResponse
import com.rondi.ronmovies.data.remote.response.ExternalResponse
import com.rondi.ronmovies.data.remote.response.GenreResponse
import com.rondi.ronmovies.data.remote.response.ImageListResponse
import com.rondi.ronmovies.data.remote.response.ImageResponse
import com.rondi.ronmovies.data.remote.response.LanguageResponse
import com.rondi.ronmovies.data.remote.response.MovieCreditsResponse
import com.rondi.ronmovies.data.remote.response.MovieDetailResponse
import com.rondi.ronmovies.data.remote.response.MovieListResponse
import com.rondi.ronmovies.data.remote.response.MovieResponse
import com.rondi.ronmovies.data.remote.response.PersonDetailResponse
import com.rondi.ronmovies.data.remote.response.PersonListResponse
import com.rondi.ronmovies.data.remote.response.PersonResponse
import com.rondi.ronmovies.data.remote.response.ReviewListResponse
import com.rondi.ronmovies.data.remote.response.ReviewResponse
import com.rondi.ronmovies.data.remote.response.SeasonDetailResponse
import com.rondi.ronmovies.data.remote.response.SeasonResponse
import com.rondi.ronmovies.data.remote.response.TvCreditsResponse
import com.rondi.ronmovies.data.remote.response.TvDetailResponse
import com.rondi.ronmovies.data.remote.response.TvListResponse
import com.rondi.ronmovies.data.remote.response.TvResponse
import com.rondi.ronmovies.data.remote.response.VideoListResponse
import com.rondi.ronmovies.data.remote.response.VideoResponse
import com.rondi.ronmovies.domain.model.AuthorDetails
import com.rondi.ronmovies.domain.model.Company
import com.rondi.ronmovies.domain.model.Country
import com.rondi.ronmovies.domain.model.Creator
import com.rondi.ronmovies.domain.model.Credits
import com.rondi.ronmovies.domain.model.Episode
import com.rondi.ronmovies.domain.model.EpisodeDetail
import com.rondi.ronmovies.domain.model.External
import com.rondi.ronmovies.domain.model.Genre
import com.rondi.ronmovies.domain.model.Image
import com.rondi.ronmovies.domain.model.ImageList
import com.rondi.ronmovies.domain.model.Language
import com.rondi.ronmovies.domain.model.Movie
import com.rondi.ronmovies.domain.model.MovieCredits
import com.rondi.ronmovies.domain.model.MovieDetail
import com.rondi.ronmovies.domain.model.MovieList
import com.rondi.ronmovies.domain.model.Person
import com.rondi.ronmovies.domain.model.PersonDetail
import com.rondi.ronmovies.domain.model.PersonList
import com.rondi.ronmovies.domain.model.Review
import com.rondi.ronmovies.domain.model.ReviewList
import com.rondi.ronmovies.domain.model.Season
import com.rondi.ronmovies.domain.model.SeasonDetail
import com.rondi.ronmovies.domain.model.Tv
import com.rondi.ronmovies.domain.model.TvCredits
import com.rondi.ronmovies.domain.model.TvDetail
import com.rondi.ronmovies.domain.model.TvList
import com.rondi.ronmovies.domain.model.Video
import com.rondi.ronmovies.domain.model.VideoList

fun CompanyResponse.toCompany(): Company = Company(
    name = name,
    originCountry = originCountry
)

fun CountryResponse.toCountry(): Country = Country(
    name = name
)

fun CreatorResponse.toCreator(): Creator = Creator(
    name = name
)

fun CreditsResponse.toCredits(): Credits = Credits(
    cast = cast.map { it.toPerson() },
    crew = crew.map { it.toPerson() },
    guestStars = guestStars?.map { it.toPerson() }
)

fun EpisodeResponse.toEpisode(): Episode = Episode(
    airDate = airDate,
    episodeNumber = episodeNumber,
    name = name,
    seasonNumber = seasonNumber,
    stillPath = stillPath,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun EpisodeDetailResponse.toEpisodeDetail(): EpisodeDetail = EpisodeDetail(
    airDate = airDate,
    credits = credits.toCredits(),
    images = images.toImageList(),
    name = name,
    overview = overview,
    runtime = runtime,
    stillPath = stillPath,
    videos = videos.toVideoList(),
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun ExternalResponse.toExternal(): External = External(
    facebookId = facebookId,
    imdbId = imdbId,
    instagramId = instagramId,
    twitterId = twitterId
)

fun GenreResponse.toGenre(): Genre = Genre(
    id = id,
    name = name
)

fun ImageResponse.toImage(): Image = Image(
    filePath = filePath
)

fun ImageListResponse.toImageList(): ImageList = ImageList(
    backdrops = backdrops?.map { it.toImage() },
    posters = posters?.map { it.toImage() },
    profiles = profiles?.map { it.toImage() },
    stills = stills?.map { it.toImage() }
)

fun LanguageResponse.toLanguage(): Language = Language(
    englishName = englishName
)

fun MovieResponse.toMovie(): Movie = Movie(
    character = character,
    id = id,
    job = job,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage
)

fun MovieCreditsResponse.toMovieCredits(): MovieCredits = MovieCredits(
    cast = cast.map { it.toMovie() },
    crew = crew.map { it.toMovie() }
)

fun MovieDetailResponse.toMovieDetail(): MovieDetail = MovieDetail(
    budget = budget,
    credits = credits.toCredits(),
    externalIds = externalIds.toExternal(),
    genres = genres.map { it.toGenre() },
    homepage = homepage,
    id = id,
    images = images.toImageList(),
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toCompany() },
    productionCountries = productionCountries.map { it.toCountry() },
    recommendations = recommendations.toMovieList(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.map { it.toLanguage() },
    status = status,
    title = title,
    videos = videos.toVideoList(),
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MovieListResponse.toMovieList(): MovieList = MovieList(
    results = results.map { it.toMovie() },
    totalResults = totalResults
)

fun PersonResponse.toPerson(): Person = Person(
    character = character,
    department = department,
    id = id,
    job = job,
    knownForDepartment = knownForDepartment,
    name = name,
    profilePath = profilePath
)

fun PersonDetailResponse.toPersonDetail(): PersonDetail = PersonDetail(
    alsoKnownAs = alsoKnownAs,
    biography = biography,
    birthday = birthday,
    deathday = deathday,
    externalIds = externalIds.toExternal(),
    gender = gender,
    homepage = homepage,
    id = id,
    images = images.toImageList(),
    knownForDepartment = knownForDepartment,
    movieCredits = movieCredits.toMovieCredits(),
    name = name,
    placeOfBirth = placeOfBirth,
    profilePath = profilePath,
    tvCredits = tvCredits.toTvCredits()
)

fun PersonListResponse.toPersonList(): PersonList = PersonList(
    results = results.map { it.toPerson() },
    totalResults = totalResults
)

fun SeasonResponse.toSeason(): Season = Season(
    airDate = airDate,
    episodeCount = episodeCount,
    name = name,
    posterPath = posterPath,
    seasonNumber = seasonNumber
)

fun SeasonDetailResponse.toSeasonDetail(): SeasonDetail = SeasonDetail(
    airDate = airDate,
    credits = credits.toCredits(),
    episodes = episodes.map { it.toEpisode() },
    images = images.toImageList(),
    name = name,
    overview = overview,
    posterPath = posterPath,
    seasonNumber = seasonNumber,
    videos = videos.toVideoList()
)

fun TvResponse.toTv(): Tv = Tv(
    character = character,
    firstAirDate = firstAirDate,
    id = id,
    job = job,
    name = name,
    overview = overview,
    posterPath = posterPath,
    voteAverage = voteAverage
)

fun TvCreditsResponse.toTvCredits(): TvCredits = TvCredits(
    cast = cast.map { it.toTv() },
    crew = crew.map { it.toTv() }
)

fun TvDetailResponse.toTvDetail(): TvDetail = TvDetail(
    createdBy = createdBy.map { it.toCreator() },
    credits = credits.toCredits(),
    episodeRunTime = episodeRunTime,
    externalIds = externalIds.toExternal(),
    firstAirDate = firstAirDate,
    genres = genres.map { it.toGenre() },
    homepage = homepage,
    id = id,
    images = images.toImageList(),
    inProduction = inProduction,
    lastAirDate = lastAirDate,
    name = name,
    networks = networks.map { it.toCompany() },
    nextEpisodeToAir = nextEpisodeToAir?.toEpisode(),
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originalName = originalName,
    overview = overview,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toCompany() },
    productionCountries = productionCountries.map { it.toCountry() },
    recommendations = recommendations.toTvList(),
    seasons = seasons.map { it.toSeason() },
    spokenLanguages = spokenLanguages.map { it.toLanguage() },
    status = status,
    videos = videos.toVideoList(),
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun TvListResponse.toTvList(): TvList = TvList(
    results = results.map { it.toTv() },
    totalResults = totalResults
)

fun VideoResponse.toVideo(): Video = Video(
    key = key,
    name = name,
    publishedAt = publishedAt,
    site = site,
    type = type
)

fun VideoListResponse.toVideoList(): VideoList = VideoList(
    results = results.map { it.toVideo() }
)

fun ReviewListResponse.toReviewList(): ReviewList = ReviewList(
    results = results.map { it.toReview() },
    totalResults = totalResults
)

fun ReviewResponse.toReview(): Review {
    return Review(
        author = author,
        authorDetails = authorDetails?.toAuthor(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url
    )
}

fun AuthorDetailsResponse.toAuthor(): AuthorDetails {
    return AuthorDetails(
        name = name,
        username = username,
        avatarPath = avatarPath,
        rating = rating
    )
}