package com.stage.imdb.data.repository

import androidx.lifecycle.LiveData
import com.stage.imdb.data.model.MediaItem

interface MediaItemRepository {
    fun getMovies(): LiveData<List<MediaItem>>
    fun getTVShows(): LiveData<List<MediaItem>>

    suspend fun findMediaItems(query: String): List<MediaItem>

    fun getMediaItem(id: Int): LiveData<MediaItem>
}