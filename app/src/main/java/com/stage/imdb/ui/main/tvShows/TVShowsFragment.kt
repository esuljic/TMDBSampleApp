package com.stage.imdb.ui.main.tvShows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.stage.imdb.R
import com.stage.imdb.ui.MediaItemAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_tvshows.recycler_view
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TVShowsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel: TvShowsViewModel by viewModels(factoryProducer = {
        val factory by instance<TvShowsViewModelFactory>()
        factory
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tvshows, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MediaItemAdapter(requireContext())
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())

        viewModel.tvShows.observe(viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
    }
}