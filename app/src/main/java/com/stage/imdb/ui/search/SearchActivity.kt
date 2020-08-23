package com.stage.imdb.ui.search

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.stage.imdb.R
import com.stage.imdb.ui.MediaItemAdapter
import com.stage.imdb.ui.base.ScopedAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.recycler_view
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class SearchActivity : ScopedAppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel: SearchViewModel by viewModels(factoryProducer = {
        val factory by instance<SearchViewModelFactory>()
        factory
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val adapter = MediaItemAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        mySearchView.setOnQueryTextFocusChangeListener { v, hasFocus -> // TODO Auto-generated method stub

        }

        mySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.length > 3) {
                    launch {
                        adapter.items = viewModel.findMediaItems(newText)
                        adapter.notifyDataSetChanged()
                    }
                }
                return false
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}