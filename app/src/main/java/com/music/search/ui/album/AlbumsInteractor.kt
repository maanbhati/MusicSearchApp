package com.music.search.ui.album

import com.music.search.models.TopAlbumsResponseResult
import io.reactivex.Single

interface AlbumsInteractor {
    fun getTopAlbums(userName: String, apiKey: String): Single<TopAlbumsResponseResult>
}