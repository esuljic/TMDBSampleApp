package com.stage.imdb.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stage.imdb.data.model.MediaItem
import com.stage.imdb.data.response.MediaItemsResponse

class IMDBDataSourceImpl(
    private val apiService: API
) : IMDBDataSource {

    //------------------------------------------------------
    //                       Subjects
    //------------------------------------------------------

    private val _downloadedMediaItems = MutableLiveData<MediaItemsResponse>()
    override val downloadedMediaItems: LiveData<MediaItemsResponse>
        get() = _downloadedMediaItems

    override suspend fun fetchTVShows() {
        val fetchedTVShows = apiService.getTVShowsAsync().await()
        _downloadedMediaItems.postValue(fetchedTVShows)
    }

    override suspend fun fetchMovies() {
        val fetchedMovies = apiService.getMoviesAsync().await()
        _downloadedMediaItems.postValue(fetchedMovies)
    }

    override suspend fun findMediaItems(query: String): List<MediaItem> {
        val results = ArrayList(apiService.findMoviesAsync(query).await().results!!)
        results.forEach {
            it.media_type = "movie"
        }
        val tvShows = apiService.findTVShowsAsync(query).await().results!!
        tvShows.forEach {
            it.media_type = "tv"
        }
        results.addAll(tvShows)
        return results
    }
}