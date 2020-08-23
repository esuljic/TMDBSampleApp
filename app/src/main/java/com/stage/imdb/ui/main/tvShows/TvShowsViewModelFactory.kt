package com.stage.imdb.ui.main.tvShows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stage.imdb.data.repository.MediaItemRepository

class TvShowsViewModelFactory(
    private val mediaItemRepository: MediaItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowsViewModel(mediaItemRepository) as T
    }
}