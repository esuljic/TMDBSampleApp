package com.stage.imdb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stage.imdb.data.model.MediaItem

@Dao
interface MediaItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(mediaItems: List<MediaItem>)

    @Query("SELECT * FROM mediaItems WHERE id = :id")
    fun getMediaItem(id: Int): LiveData<MediaItem>

    @get:Query("SELECT * FROM mediaItems WHERE media_type = 'movie'")
    val getMovies: LiveData<List<MediaItem>>

    @get:Query("SELECT * FROM mediaItems WHERE media_type = 'tv'")
    val getTvShows: LiveData<List<MediaItem>>
}