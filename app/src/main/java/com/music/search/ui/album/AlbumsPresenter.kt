package com.music.search.ui.album

interface AlbumsPresenter {
    fun onDestroy()
    fun getTopAlbums(userName: String, apiKey: String)
}