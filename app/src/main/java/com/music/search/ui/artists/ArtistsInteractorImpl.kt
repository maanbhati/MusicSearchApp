package com.music.search.ui.artists

import com.music.search.models.TopArtistsResponseResult
import com.music.search.network.TopArtistsService
import io.reactivex.Single
import retrofit2.Retrofit

class ArtistsInteractorImpl(var mRetrofit: Retrofit) : ArtistsInteractor {
    override fun getTopArtists(userName: String, apiKey: String): Single<TopArtistsResponseResult> {
        return mRetrofit.create(TopArtistsService::class.java).getTopArtists(userName, apiKey)
    }
}