package com.music.search.ui.tracks

interface TracksPresenter {
    fun onDestroy()
    fun getTracks(userName: String, apiKey: String)
}