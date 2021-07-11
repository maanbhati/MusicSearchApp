package com.music.search.network

import com.music.search.models.TopTracksResponseResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TopTracksService {
    @GET("?method=track.search&format=json")
    fun getTopTracks(
        @Query("track") user: String,
        @Query("api_key") apiKey: String
    ): Single<TopTracksResponseResult>
}