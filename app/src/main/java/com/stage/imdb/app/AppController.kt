package com.stage.imdb.app

import android.app.Application
import com.stage.imdb.data.AppDatabase
import com.stage.imdb.data.network.API
import com.stage.imdb.data.network.IMDBDataSource
import com.stage.imdb.data.network.IMDBDataSourceImpl
import com.stage.imdb.data.repository.MediaItemRepository
import com.stage.imdb.data.repository.MediaItemRepositoryImpl
import com.stage.imdb.ui.main.movies.MoviesViewModelFactory
import com.stage.imdb.ui.main.tvShows.TvShowsViewModelFactory
import com.stage.imdb.ui.mediaItem.MediaItemViewModelFactory
import com.stage.imdb.ui.search.SearchViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class AppController : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppController))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { instance<AppDatabase>().moviesDao() }
        bind() from singleton { API() }
        bind<IMDBDataSource>() with singleton { IMDBDataSourceImpl(instance()) }
        bind<MediaItemRepository>() with singleton {
            MediaItemRepositoryImpl(
                instance(),
                instance()
            )
        }
        bind() from provider { MoviesViewModelFactory(instance()) }
        bind() from provider { TvShowsViewModelFactory(instance()) }
        bind() from provider { SearchViewModelFactory(instance()) }
        bind() from factory { id: Int -> MediaItemViewModelFactory(instance(), id) }
    }
}