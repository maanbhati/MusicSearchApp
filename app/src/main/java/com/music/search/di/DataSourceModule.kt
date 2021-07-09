package com.music.search.di

import com.music.search.domain.data.ISearchDataSource
import com.music.search.domain.data.SearchDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<ISearchDataSource> { SearchDataSourceImpl(get()) }
}