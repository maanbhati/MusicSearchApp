package com.music.search

import android.app.Application
import com.music.search.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MusicSearchApp : Application() {
    private val modules = listOf(
        apiModule,
        dataSourceModule,
        repositoryModule,
        useCasesModule,
        viewModelModule,
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules)
        }
    }
}