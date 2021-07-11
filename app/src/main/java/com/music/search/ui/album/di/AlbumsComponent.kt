package com.music.search.ui.album.di

import com.music.search.ui.album.AlbumsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AlbumsModule::class])
interface AlbumsComponent {
    fun inject(target: AlbumsFragment)
}