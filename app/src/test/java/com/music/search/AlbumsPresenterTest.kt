package com.music.search

import com.music.search.ui.album.AlbumsFragment
import com.music.search.ui.album.AlbumsInteractorImpl
import com.music.search.ui.album.AlbumsPresenterImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AlbumsPresenterTest {
    lateinit var albumsPresenter: AlbumsPresenterImpl

    @Before
    fun setUp() {
        val fragmentMock = Mockito.mock(AlbumsFragment::class.java)
        val topAlbumsInteractorMock = Mockito.mock(AlbumsInteractorImpl::class.java)
        albumsPresenter = AlbumsPresenterImpl(fragmentMock, topAlbumsInteractorMock)
    }

    @Test
    fun test_getAlbums_calls_the_update_method_once_on_view() {
        `when`(albumsPresenter.getAlbums("b", "mockApiKey")).then {
            verify(albumsPresenter.mView.updateData(mutableListOf()), Mockito.times(1))
        }
    }
}