package com.music.search.ui.tracks

interface TracksPresenter {
    fun onDestroy()
    fun getTopTracks(userName: String, apiKey: String)
}