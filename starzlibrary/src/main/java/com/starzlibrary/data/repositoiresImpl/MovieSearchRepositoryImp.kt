package com.starzlibrary.data.repositoiresImpl

import com.starzlibrary.data.remote.MovieApiDefinition
import com.starzlibrary.domain.repositories.MovieSearchRepository
import javax.inject.Inject

class MovieSearchRepositoryImp @Inject constructor(private val movieApiDefinition: MovieApiDefinition) :
    MovieSearchRepository {

    override fun search(apiKey: String, query: String) = movieApiDefinition.search(apiKey, query)


}