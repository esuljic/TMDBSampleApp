package com.stage.imdb.ui.mediaItem

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.stage.imdb.R
import kotlinx.android.synthetic.main.activity_media_item.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MediaItemActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel: MediaItemViewModel by viewModels(factoryProducer = {
        val factory by instance<Int, MediaItemViewModelFactory>(
            arg = intent.getIntExtra(
                "mediaItemId",
                0
            )
        )
        factory
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_item)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.mediaItem.observe(this, Observer {
            title_view.text = if (it.media_type == "tv") it.name else it.title
            poster_view.load("https://image.tmdb.org/t/p/w500${it.poster_path}")
            overview_view.text = it.overview
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}