package com.music.search.ui.album

interface AlbumsPresenter {
    fun onDestroy()
    fun getAlbums(userName: String, apiKey: String)
}