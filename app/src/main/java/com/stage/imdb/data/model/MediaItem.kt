package com.stage.imdb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "mediaItems")
class MediaItem(
    @PrimaryKey
    val id: Int = -1,
    val name: String? = "",
    val title: String? = "",
    val poster_path: String? = "",
    val backdrop_path: String? = "",
    val overview: String? = "",
    var media_type: String? = ""
) : Serializable