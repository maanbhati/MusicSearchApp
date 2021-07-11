package com.music.search.ui.tracks.di

import com.music.search.ui.tracks.TracksFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TracksModule::class])
interface TracksComponent {
    fun inject(target: TracksFragment)
}