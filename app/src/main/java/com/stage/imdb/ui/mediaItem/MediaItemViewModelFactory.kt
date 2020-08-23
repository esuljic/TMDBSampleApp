package com.stage.imdb.ui.mediaItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stage.imdb.data.repository.MediaItemRepository

class MediaItemViewModelFactory(
    private val mediaItemRepository: MediaItemRepository,
    private val id: Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MediaItemViewModel(mediaItemRepository, id) as T
    }
}