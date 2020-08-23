package com.stage.imdb.ui.search

import androidx.lifecycle.ViewModel
import com.stage.imdb.data.repository.MediaItemRepository

class SearchViewModel(
    private val mediaItemRepository: MediaItemRepository
) : ViewModel() {

    suspend fun findMediaItems(query: String) = mediaItemRepository.findMediaItems(query)
}