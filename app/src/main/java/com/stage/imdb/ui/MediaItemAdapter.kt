package com.stage.imdb.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.stage.imdb.R
import com.stage.imdb.data.model.MediaItem
import com.stage.imdb.ui.mediaItem.MediaItemActivity
import kotlinx.android.synthetic.main.item_mediaitem.view.*
import java.util.*

class MediaItemAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<MediaItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_mediaitem, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userViewHolder = holder as ItemViewHolder
        val item = items[position]

        userViewHolder.itemView.item_image.load("https://image.tmdb.org/t/p/w500${item.backdrop_path}")

        userViewHolder.itemView.item_title.text =
            if (item.media_type == "tv") item.name else item.title

        userViewHolder.itemView.setOnClickListener {
            context.startActivity(Intent(context, MediaItemActivity::class.java).apply {
                putExtra("mediaItemId", item.id)
            })
        }
    }

    override fun getItemCount() = items.size

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}