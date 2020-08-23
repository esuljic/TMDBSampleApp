package com.stage.imdb.data.repository

import androidx.lifecycle.LiveData
import com.stage.imdb.data.MediaItemDao
import com.stage.imdb.data.model.MediaItem
import com.stage.imdb.data.network.IMDBDataSource
import com.stage.imdb.data.response.MediaItemsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MediaItemRepositoryImpl(
    private val mediaItemDao: MediaItemDao,
    private val imdbDataSource: IMDBDataSource
) : MediaItemRepository {

    init {
        imdbDataSource.apply {
            downloadedMediaItems.observeForever {
                persistFetchedSubjects(it)
            }
        }
    }

    private fun persistFetchedSubjects(fetchedMediaItems: MediaItemsResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            mediaItemDao.upsert(fetchedMediaItems.results!!)
        }
    }

    override fun getMovies(): LiveData<List<MediaItem>> {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                imdbDataSource.fetchMovies()
            }
        }
        return mediaItemDao.getMovies
    }

    override fun getTVShows(): LiveData<List<MediaItem>> {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                imdbDataSource.fetchTVShows()
            }
        }
        return mediaItemDao.getTvShows
    }

    override suspend fun findMediaItems(query: String) = imdbDataSource.findMediaItems(query).apply {
        GlobalScope.launch(Dispatchers.IO) {
            mediaItemDao.upsert(this@apply)
        }
    }

    override fun getMediaItem(id: Int) = mediaItemDao.getMediaItem(id)
}