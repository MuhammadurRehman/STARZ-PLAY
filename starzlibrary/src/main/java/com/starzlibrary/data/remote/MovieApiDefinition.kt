package com.starzlibrary.data.remote


import com.starzlibrary.data.models.SearchMoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiDefinition {
    @Headers("Accept: application/json")
    @GET("/3/search/multi")
    fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<SearchMoviesModel>
}