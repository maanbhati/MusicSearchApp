package com.music.search.ui.artists

import com.music.search.models.Artist

interface ArtistsView {
    fun showProgress()
    fun hideProgress()
    fun updateData(topArtists: MutableList<Artist>)
    fun showError()
    fun showEmpty()
    fun hidEmpty()
}