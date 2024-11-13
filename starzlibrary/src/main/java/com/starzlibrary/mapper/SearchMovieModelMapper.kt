package com.starzlibrary.mapper


fun com.starzlibrary.data.models.Movie.toDomainModel() = com.starzlibrary.domain.models.Movie(
    backdropPath,
    id ?: 0,
    title,
    originalTitle,
    overview,
    posterPath,
    mediaType,
    adult ?: false,
    originalLanguage ?: "",
    genreIds ?: listOf(),
    popularity ?: 0.0,
    releaseDate ?: "",
    video ?: false,
    voteAverage ?: 0.0,
    voteCount ?: 0
)

fun List<com.starzlibrary.data.models.Movie>.toDomainModel() = map { it.toDomainModel() }

fun com.starzlibrary.data.models.SearchMoviesModel.toDomainModel(): com.starzlibrary.domain.models.SearchMoviesModel =
    com.starzlibrary.domain.models.SearchMoviesModel(
        page,
        results = results.toDomainModel(),
        totalPages,
        totalResults
    )