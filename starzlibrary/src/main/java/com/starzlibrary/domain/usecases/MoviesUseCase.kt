package com.starzlibrary.domain.usecases

import API_KEY
import POST_BASE_URL
import android.util.Log
import com.starzlibrary.core.Result
import com.starzlibrary.data.models.SearchMoviesModel
import com.starzlibrary.domain.repositories.MovieSearchRepository
import com.starzlibrary.mapper.toDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class MoviesUseCase @Inject constructor(private val movieSearchRepository: MovieSearchRepository) {

    private val _queryResponseState = MutableStateFlow<Result<Any>?>(null)
    val queryResponseState = _queryResponseState.asStateFlow()


    fun searchQuery(query: String) {
        try {
            val request = movieSearchRepository.search(API_KEY, query)

            request.enqueue(object : retrofit2.Callback<SearchMoviesModel> {
                override fun onResponse(
                    call: Call<SearchMoviesModel>,
                    response: Response<SearchMoviesModel>
                ) {
                    try {
                        if (response.body() != null) {
                            val result = response.body()?.toDomainModel()
                            result?.let {
                                it?.results?.map {
                                    if(it.posterPath!=null)
                                        it.posterPath = POST_BASE_URL + it.posterPath
                                }
                                _queryResponseState.value = Result.Success(it.results)
                            }
                        } else {
                            _queryResponseState.value =
                                Result.Error("Error: ${response.code()} ${response.message()}")
                        }
                        Log.d("UserQueryUseCase_sendQuery", "success ${response.body()}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Result.Error("Error:  ${e.message}")

                    }

                }

                override fun onFailure(call: Call<SearchMoviesModel>, t: Throwable) {
                    Log.e("UserQueryUseCase_sendQuery", "Fail ${t.message}")
                    _queryResponseState.value = Result.Error("Error:${t.message}")

                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            _queryResponseState.value = Result.Error(e.message.toString())
        }
    }
}
