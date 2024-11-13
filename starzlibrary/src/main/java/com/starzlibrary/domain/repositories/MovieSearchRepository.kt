package com.starzlibrary.domain.repositories

import com.starzlibrary.data.models.SearchMoviesModel
import retrofit2.Call

interface MovieSearchRepository {
    fun search(apiKey: String, query: String): Call<SearchMoviesModel>
}