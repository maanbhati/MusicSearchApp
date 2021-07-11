package com.music.search.ui.artists.di

import com.music.search.ui.artists.ArtistsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ArtistsModule::class])
interface ArtistsComponent {
    fun inject(target: ArtistsFragment)
}