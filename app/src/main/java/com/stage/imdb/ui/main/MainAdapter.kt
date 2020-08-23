package com.stage.imdb.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.stage.imdb.ui.main.movies.MoviesFragment
import com.stage.imdb.ui.main.tvShows.TVShowsFragment

class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> MoviesFragment()
        else -> TVShowsFragment()
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Movies"
        1 -> "TV Shows"
        else -> ""
    }

    override fun getCount(): Int = 2
}