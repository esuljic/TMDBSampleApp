package com.stage.imdb.ui.main.movies

import androidx.lifecycle.ViewModel
import com.stage.imdb.data.repository.MediaItemRepository

class MoviesViewModel(
    private val mediaItemRepository: MediaItemRepository
) : ViewModel() {

    val movies = mediaItemRepository.getMovies()
}