package com.music.search.ui.artists

interface ArtistsPresenter {
    fun onDestroy()
    fun getArtists(userName: String, apiKey: String)
}