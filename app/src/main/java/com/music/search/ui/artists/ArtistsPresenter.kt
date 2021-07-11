package com.music.search.ui.artists

interface ArtistsPresenter {
    fun onDestroy()
    fun getUserTopArtists(userName: String, apiKey: String)
}