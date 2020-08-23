package com.stage.imdb.ui.main.tvShows

import androidx.lifecycle.ViewModel
import com.stage.imdb.data.repository.MediaItemRepository

class TvShowsViewModel(
    private val mediaItemRepository: MediaItemRepository
) : ViewModel() {

    val tvShows = mediaItemRepository.getTVShows()
}