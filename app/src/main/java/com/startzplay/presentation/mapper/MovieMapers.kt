package com.startzplay.presentation.mapper

fun com.starzlibrary.domain.models.Movie.toPresentationModel() =
    com.startzplay.presentation.models.Movie(title?:"", posterPath?:"", mediaType?:"", overview?:"")

fun List<com.starzlibrary.domain.models.Movie>.toPresentationModel() = map { it.toPresentationModel() }