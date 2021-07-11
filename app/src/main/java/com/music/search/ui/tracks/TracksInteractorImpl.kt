package com.music.search.ui.tracks

import com.music.search.models.TopTracksResponseResult
import com.music.search.network.TopTracksService
import io.reactivex.Single
import retrofit2.Retrofit

class TracksInteractorImpl(var mRetrofit: Retrofit) : TracksInteractor {
    override fun getTopTracks(userName: String, apiKey: String): Single<TopTracksResponseResult> {
        return mRetrofit.create(TopTracksService::class.java).getTopTracks(userName, apiKey)
    }
}