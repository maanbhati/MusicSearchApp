package com.music.search.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.music.search.R
import com.music.search.ui.album.AlbumsFragment
import com.music.search.ui.artists.ArtistsFragment
import com.music.search.ui.tracks.TracksFragment
import java.lang.ref.WeakReference
import java.util.*

open class MusicSearchPagerAdapter(fm: FragmentManager?, context: Context) :
    FragmentPagerAdapter(fm!!) {
    private var topArtistsTitle: String = context.getString(R.string.title_artists)
    private val topTracksTitle: String = context.getString(R.string.title_tracks)
    private val topAlbumsTitle: String = context.getString(R.string.title_albums)
    private var fragments: Hashtable<Int, WeakReference<Fragment>> = Hashtable()
    override fun getItem(position: Int): Fragment {
        return when (position) {
            TOP_ARTISTS_INDEX -> {
                val fr: Fragment = ArtistsFragment.newInstance()
                fragments[position] = WeakReference(fr)
                fr
            }
            TOP_ALBUMS_INDEX -> {
                val fr: Fragment = AlbumsFragment.newInstance()
                fragments[position] = WeakReference(fr)
                fr
            }
            TOP_TRACKS_INDEX -> {
                val fr: Fragment = TracksFragment.newInstance()
                fragments[position] = WeakReference(fr)
                fr
            }
            else -> {
                ArtistsFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            TOP_ARTISTS_INDEX -> {
                topArtistsTitle
            }
            TOP_ALBUMS_INDEX -> {
                topAlbumsTitle
            }
            TOP_TRACKS_INDEX -> {
                topTracksTitle
            }
            else -> {
                super.getPageTitle(position)
            }
        }
    }

    override fun getCount(): Int {
        return NUMBER_OF_ITEMS
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        fragments.remove(position)
    }

    fun getFragments(): ArrayList<Fragment?> {
        val list = ArrayList<Fragment?>()
        for (i in 0 until fragments.size) {
            list.add(fragments[i]?.get())
        }
        return list
    }

    companion object {
        private const val NUMBER_OF_ITEMS = 3
        private const val TOP_ARTISTS_INDEX = 2
        private const val TOP_ALBUMS_INDEX = 0
        private const val TOP_TRACKS_INDEX = 1
    }
}