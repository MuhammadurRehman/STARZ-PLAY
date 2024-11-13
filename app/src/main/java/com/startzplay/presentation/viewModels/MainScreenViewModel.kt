package com.startzplay.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startzplay.presentation.mapper.toPresentationModel
import com.startzplay.presentation.models.Movie
import com.starzlibrary.core.Result
import com.starzlibrary.domain.usecases.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {

    private val _moviesState = MutableStateFlow<Map<String, List<Movie>>>(emptyMap())
    val moviesState = _moviesState.asStateFlow()

    init {
        viewModelScope.launch {
            moviesUseCase.queryResponseState.collectLatest {
                when (it) {
                    is Result.Success -> {
                        val result = it as Result.Success<*>
                        val movies = result.data as List<com.starzlibrary.domain.models.Movie>
                        val moviesList = movies.toPresentationModel()
                        val carouselGroups = moviesList.groupBy { it.mediaType }.toSortedMap().toMap()
                        _moviesState.value = carouselGroups
                    }

                    is Result.Error -> {

                    }

                    else -> {}
                }
            }
        }
    }

    fun searchQuery(query: String) {
        moviesUseCase.searchQuery(query)
    }
}