package com.music.search.ui.tracks

import com.music.search.models.Track

interface TracksView {
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun updateData(tracks: MutableList<Track>)
    fun showEmpty()
    fun hidEmpty()
}