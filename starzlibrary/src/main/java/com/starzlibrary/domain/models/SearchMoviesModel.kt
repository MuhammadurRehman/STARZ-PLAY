package com.starzlibrary.domain.models


data class SearchMoviesModel(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)

data class Movie(
    val backdropPath: String?,
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val overview: String?,
    var posterPath: String?,
    val mediaType: String?,
    val adult: Boolean,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val releaseDate: String?,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    )