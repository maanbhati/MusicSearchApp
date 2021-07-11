package com.music.search.ui.artists

import com.music.search.models.TopArtistsResponseResult
import io.reactivex.Single

interface ArtistsInteractor {
    fun getTopArtists(userName: String, apiKey: String): Single<TopArtistsResponseResult>
}