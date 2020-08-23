package com.stage.imdb.data.network

import androidx.lifecycle.LiveData
import com.stage.imdb.data.model.MediaItem
import com.stage.imdb.data.response.MediaItemsResponse

interface IMDBDataSource {

    //Subjects
    val downloadedMediaItems: LiveData<MediaItemsResponse>

    suspend fun fetchTVShows()
    suspend fun fetchMovies()

    suspend fun findMediaItems(query: String): List<MediaItem>
}