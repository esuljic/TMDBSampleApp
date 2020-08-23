package com.stage.imdb.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stage.imdb.data.repository.MediaItemRepository

class SearchViewModelFactory(
    private val mediaItemRepository: MediaItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(mediaItemRepository) as T
    }
}