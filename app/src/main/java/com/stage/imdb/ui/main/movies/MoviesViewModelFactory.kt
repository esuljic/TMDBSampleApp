package com.stage.imdb.ui.main.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stage.imdb.data.repository.MediaItemRepository

class MoviesViewModelFactory(
    private val mediaItemRepository: MediaItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(mediaItemRepository) as T
    }
}