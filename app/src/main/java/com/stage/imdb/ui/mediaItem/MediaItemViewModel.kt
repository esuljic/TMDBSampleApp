package com.stage.imdb.ui.mediaItem

import androidx.lifecycle.ViewModel
import com.stage.imdb.data.repository.MediaItemRepository

class MediaItemViewModel(
    private val mediaItemRepository: MediaItemRepository,
    private val id: Int
) : ViewModel() {

    val mediaItem = mediaItemRepository.getMediaItem(id)
}