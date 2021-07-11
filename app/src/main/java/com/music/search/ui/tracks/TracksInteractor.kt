package com.music.search.ui.tracks

import com.music.search.models.TopTracksResponseResult
import io.reactivex.Single

interface TracksInteractor {
    fun getTopTracks(userName: String, apiKey: String): Single<TopTracksResponseResult>
}