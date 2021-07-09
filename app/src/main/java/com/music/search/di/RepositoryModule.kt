package com.music.search.di

import com.music.search.domain.repository.ISearchRepository
import com.music.search.domain.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ISearchRepository> { SearchRepository(get(), get()) }
}
