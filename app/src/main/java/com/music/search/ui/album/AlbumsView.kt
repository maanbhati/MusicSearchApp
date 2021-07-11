package com.music.search.ui.album

import com.music.search.models.Album

interface AlbumsView {
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun updateData(topAlbums: MutableList<Album>)
    fun showEmpty()
    fun hidEmpty()
}