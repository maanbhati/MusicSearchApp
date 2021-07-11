package com.music.search.ui.album

import com.music.search.models.TopAlbumsResponseResult
import com.music.search.network.TopAlbumsService
import io.reactivex.Single
import retrofit2.Retrofit

class AlbumsInteractorImpl(var mRetrofit: Retrofit) : AlbumsInteractor {
    override fun getTopAlbums(userName: String, apiKey: String): Single<TopAlbumsResponseResult> {
        return mRetrofit.create(TopAlbumsService::class.java).getTopArtists(userName, apiKey)
    }
}